/*package cn.web;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebAppInit implements WebApplicationInitializer {

	public void onStartup(ServletContext context) throws ServletException {
		// add listener
		context.setInitParameter("contextConfigLocation",
				"classpath:spring-mybatis.xml");
		context.addListener(new ContextLoaderListener());

		// 申明DispatcherServlet，并设置参数
		ServletRegistration.Dynamic reg = context.addServlet("springmvc",
				new DispatcherServlet());
		reg.setLoadOnStartup(1);
		reg.addMapping("/");

		// 指明springmvc配置文件路径
		reg.setInitParameter("contextConfigLocation",
				"classpath:springmvc-servlet.xml");
		
		//添加字符编码的filter
		FilterRegistration.Dynamic filterReg = 
				context.addFilter("encodingFilter", 
						new CharacterEncodingFilter());
		filterReg.setInitParameter("encoding", "utf-8");
		filterReg.addMappingForServletNames(null, true, "/*");

	}


}
*/