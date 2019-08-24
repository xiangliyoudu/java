package controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pojo.User;
import utils.PasswordUtil;
import dao.UserDao;

@Controller
public class IndexController {
	
	@Autowired
	UserDao userDao;

	@RequestMapping({"/login", "/"})
	public String loginPage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
//System.out.println("index page");
		return "login";
	}

	@RequestMapping(value = "/dologin", method = { RequestMethod.POST, RequestMethod.GET })
	public Object doLogin(User user, RedirectAttributes model) {
		String msg = null;
//System.out.println(user);

		try {
			String salt = userDao.findByUsername(user.getUsername()).getSalt();
			String encodePassword = PasswordUtil.encryptPassword(user.getPassword(), salt);
//System.out.println(encodePassword);
			UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(), encodePassword.toCharArray());
			Subject subject = SecurityUtils.getSubject();

			subject.login(token);
		} catch (UnknownAccountException e) {
			msg = "’À∫≈≤ª¥Ê‘⁄";
			model.addAttribute("msg", msg);
			return "login";
		} catch (IncorrectCredentialsException e) {
			msg = "√‹¬Î¥ÌŒÛ";
			model.addAttribute("msg", msg);
			return "login";
		} catch (Exception e) {
			return "login";
		}
//System.out.println(msg);

		return "redirect:main";
	}

	@ResponseBody
	@RequestMapping(value = "/main")
	public Object main(Model model) {

		return "show all users";
	}
	
	
	@RequestMapping("/logout")
	public Object logout() {
		return "redirect:login";
	}

}
