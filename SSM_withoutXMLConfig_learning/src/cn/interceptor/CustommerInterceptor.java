package cn.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tool.Constants;
import cn.pojo.BackendUser;
import cn.pojo.DevUser;

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
