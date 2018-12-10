package com.xlyd.springboot.app_platform.interceptor;

import com.xlyd.springboot.app_platform.entity.BackendUser;
import com.xlyd.springboot.app_platform.entity.DevUser;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.xlyd.springboot.app_platform.tool.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustommerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		//获取Session对象

		HttpSession session = request.getSession();
		//获取用户登录对象
		BackendUser backendUser = 
				(BackendUser) session.getAttribute(Constants.USER_SESSION);
		DevUser devUser = 
				(DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
		
		//进行验证，并跳转页面
		if(null != backendUser) {
			return true;
		} else if (null != devUser) {
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/403.jsp");
			return false;
		}
	}

	


}
