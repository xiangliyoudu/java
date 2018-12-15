package config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@ComponentScan(basePackages = { "controller" })
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	/**
	 * 视图解析器
	 * 
	 * @return
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/jsp/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// fastJson 消息转换器
		FastJsonHttpMessageConverter jsonMessageConverter = new FastJsonHttpMessageConverter();
		// fastJson config
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setCharset(Charset.forName("utf-8"));
		jsonMessageConverter.setFastJsonConfig(fastJsonConfig);
		// media type
		List<MediaType> jsonMediaTypes = new ArrayList<>();
		jsonMediaTypes.add(MediaType.APPLICATION_JSON);
		jsonMediaTypes.add(MediaType.TEXT_HTML);
		jsonMessageConverter.setSupportedMediaTypes(jsonMediaTypes);
		// 添加消息转换器
		converters.add(jsonMessageConverter);
	}

}
