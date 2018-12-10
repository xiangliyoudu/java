package cn.control;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tool.Constants;

@Controller
public class AppUserLogout {

	@RequestMapping(value={"/dev/logout","/manager/logout"})
	public String logout(HttpSession session) {
		if (Constants.USER_SESSION != null) {
			session.removeAttribute(Constants.USER_SESSION);
		}
		
		if (Constants.DEV_USER_SESSION != null) {
			session.removeAttribute(Constants.DEV_USER_SESSION);
		}
		
		return "redirect:/index.jsp";
	}
}
