package com.xlyd.springboot.app_platform.control.developer;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xlyd.springboot.app_platform.tool.Constants;
import com.xlyd.springboot.app_platform.entity.DevUser;
import com.xlyd.springboot.app_platform.service.IDevUserService;

@Controller
@Scope(value="prototype")
@RequestMapping("/dev")
public class AppDevLoginController {
	
	@Resource
	private IDevUserService iDevUserService;
	/**
	 * devUser登录页面
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("devlogin");
		return mav;
	}
	
	/**
	 * 进行devUser登录验证
	 * @return
	 */
	@RequestMapping(value="/dologin", method = RequestMethod.POST)
	public ModelAndView dologin(String devCode,String devPassword,
								HttpSession session) {
		ModelAndView mav = new ModelAndView();
		
		DevUser devUser = 
				iDevUserService.findByNameAndPwd(devCode, devPassword);

		if (devUser != null) {
			session.setAttribute(Constants.DEV_USER_SESSION, devUser);
			mav.setViewName("developer/main");
		} else {
			mav.addObject("error", "用户名或密码错误！！");
			mav.setViewName("devlogin");
		}
		
		return mav;
	}
		
}



