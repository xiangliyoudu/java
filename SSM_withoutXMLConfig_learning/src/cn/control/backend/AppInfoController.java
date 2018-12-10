package cn.control.backend;

import tool.Constants;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.pojo.BackendUser;
import cn.service.IBackendUserService;

@Controller
@RequestMapping("/manager")
public class AppInfoController {
	
	@Resource
	private IBackendUserService iBackendUserService;
	/**
	 * backend
	 * @return
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView mlogin() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backendlogin");
		return mav;
	}
	
	@RequestMapping(value="/dologin", method = RequestMethod.POST)
	public ModelAndView mdoLogin(@RequestParam String userCode,
								 @RequestParam String userPassword,
								 HttpSession session) {
		//待完善，需要查询出用户类型名称
		BackendUser bUser = 
				iBackendUserService.findByNameAndPwd(userCode, userPassword);

		ModelAndView mav = new ModelAndView();
		if (bUser != null) {
			mav.setViewName("backend/main");
			session.setAttribute(Constants.USER_SESSION, bUser);

		} else {
			mav.setViewName("redirect:/403.jsp");
		}
		return mav;
	}
	
	
	@ExceptionHandler(RuntimeException.class)
	public String handleException() {
		return "error";
	}
	
	@RequestMapping("/do_login")
	public String doLogin(RedirectAttributes model,
						 @RequestParam String userCode,
			             @RequestParam String userPassword) {
		String url = "/login";
		BackendUser bUser = 
				iBackendUserService.findByNameAndPwd(userCode, userPassword);
		
		if (bUser != null) {
			model.addFlashAttribute("bUser", bUser);
			url = "redirect:backend/main";
		}
		
		return url;
	}

	
}
