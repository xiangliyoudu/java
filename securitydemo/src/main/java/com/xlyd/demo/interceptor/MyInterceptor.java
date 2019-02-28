package com.xlyd.demo.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xlyd.demo.util.LogUtils;

public class MyInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = LogUtils.getLog(MyInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String msg = request.getLocalAddr();
		log.info("$$Intercepter msg: " + msg);
		return true;

	}

}
