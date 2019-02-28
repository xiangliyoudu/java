package com.xlyd.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.xlyd.demo.interceptor.MyInterceptor;

@Configuration
public class MVCConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		// add intercepter
		HandlerInterceptor interceptor = new MyInterceptor();
		registry.addInterceptor(interceptor).addPathPatterns("/test/**");
	}

}
