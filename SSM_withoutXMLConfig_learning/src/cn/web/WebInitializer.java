package cn.web;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import cn.config.RootConfig;
import cn.config.WebConfig;

public class WebInitializer extends
		AbstractAnnotationConfigDispatcherServletInitializer {
	
	// 非web bean配置类
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[]{RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[]{WebConfig.class};
	}
	
	// DispatcherServlet 处理的映射
	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

	// 自定义注册
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// 容器在启动的时候加载这个servlet的优先级
		registration.setLoadOnStartup(1);
		// 文件上传配置信息
		registration.setMultipartConfig(
				new MultipartConfigElement("/statics/uploadfiles", 2*1024*1024, 5*1024*1024, 0));
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = 
				new CharacterEncodingFilter("utf-8");
		return new Filter[]{encodingFilter };
	}
	
	

}
