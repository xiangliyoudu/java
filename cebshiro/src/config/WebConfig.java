package config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
@ComponentScan(basePackages = { "controller" })
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

//	@Bean
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//		resolver.setPrefix("/WEB-INF/jsp/");
//		resolver.setSuffix(".jsp");
//		return resolver;
//	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// fastJson 锟斤拷息转锟斤拷锟斤拷
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
		// 锟斤拷锟斤拷锟较⒆拷锟斤拷锟�
		converters.add(jsonMessageConverter);
	}

	
	/**
	 * thymeleaf 锟斤拷图锟斤拷锟斤拷锟斤拷
	 *
	 * @param engine
	 * @return
	 */
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine engine) {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(engine);
		resolver.setCharacterEncoding("utf-8");
		resolver.setOrder(1);
		return resolver;
	}

	/**
	 * themeleaf 模锟斤拷锟斤拷锟斤拷
	 * @param resolver
	 * @return
	 */
	@Bean
	public SpringTemplateEngine templateEngine(ITemplateResolver resolver) {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(resolver);
		engine.setDialect(new SpringStandardDialect());
//		engine.setDialect(new SpringSecurityDialect());
		return engine;
	}

	/**
	 * thymeleaf 模锟斤拷锟斤拷锟斤拷锟�
	 *
	 * @param servletContext
	 * @return
	 */
	@Bean
	public ITemplateResolver iTemplateResolver(ServletContext servletContext) {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver(
				servletContext);
		resolver.setPrefix("/WEB-INF/template/");
		resolver.setSuffix(".html");
		resolver.setTemplateMode(TemplateMode.HTML);
		return resolver;
	}
}
