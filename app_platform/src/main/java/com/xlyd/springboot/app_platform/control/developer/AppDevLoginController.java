package com.xlyd.springboot.app_platform.control.developer;

import com.xlyd.springboot.app_platform.entity.DevUser;
import com.xlyd.springboot.app_platform.service.IDevUserService;
import com.xlyd.springboot.app_platform.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dev")
public class AppDevLoginController {

    @Resource
    private IDevUserService iDevUserService;

    /**
     * devUser登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String doLogin() {
        return "devlogin";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String doMain() {
        return "developer/main";
    }

    /**
     * 进行devUser登录验证
     *
     * @return
     */
    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String dologin(String devCode,
                          String devPassword,
                          HttpSession session,
                          RedirectAttributes model) {
        String mav;
        DevUser devUser = iDevUserService.findByNameAndPwd(devCode, devPassword);

        if (devUser != null) {
            model.addFlashAttribute(Constants.DEV_USER_SESSION, devUser);
            session.setAttribute(Constants.DEV_USER_SESSION, devUser);
            mav = "redirect:/dev/main";
        } else {
            model.addAttribute("error", "用户名或密码错误！！");
            mav = "devlogin";
        }

        return mav;
    }

}



