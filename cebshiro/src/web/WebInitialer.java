package web;

import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import config.RootConfig;
import config.ShiroConfig;
import config.WebConfig;

public class WebInitialer extends
		AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class, ShiroConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// ע��shirofilter, ���ƺ�shiroFilter bean����һ��
		Dynamic shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
		shiroFilter.setInitParameter("targetFilterLifecycle", "true");
		// ӳ��·����������������
		shiroFilter.addMappingForUrlPatterns(null, true, "/*");
		super.onStartup(servletContext);
	}
	
	
	
	


}
