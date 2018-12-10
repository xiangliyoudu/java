package webinitializertest;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import cn.interceptor.CustommerInterceptor;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
// 启用SpringMVC
@EnableWebMvc
@ComponentScan({"cn.control"})
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/** 视图解析器
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	// 静态资源不由dispatchservlet处理
	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	// 文件上传解析器
	@Bean
	public MultipartResolver multipartResolver() {
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
	}
	
	// 添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		CustommerInterceptor intercepter = new CustommerInterceptor();
		super.addInterceptors(registry);
		registry.addInterceptor(intercepter)
			.addPathPatterns("/manager/backend/**", "/dev/flatform/**");
	}
	
	/**
	 * 扩展默认的消息转换器
	 */
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// fastJson 消息转换器
		FastJsonHttpMessageConverter jsonMessageConverter = 
				new FastJsonHttpMessageConverter();
		// fastJson config
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(Charset.forName("utf-8"));
		jsonMessageConverter.setFastJsonConfig(fastJsonConfig);
		// media type
		List<MediaType> jsonMediaTypes = new ArrayList<>();
		jsonMediaTypes.add(MediaType.APPLICATION_JSON);
		jsonMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		jsonMediaTypes.add(MediaType.TEXT_HTML);
		jsonMessageConverter.setSupportedMediaTypes(jsonMediaTypes);
		// 添加消息转换器
		converters.add(jsonMessageConverter);
	}
	
}











