package com.xlyd.springboot.app_platform.control.backend;

import com.xlyd.springboot.app_platform.entity.BackendUser;
import com.xlyd.springboot.app_platform.service.IBackendUserService;
import com.xlyd.springboot.app_platform.tool.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class AppInfoController {

    @Autowired
    private IBackendUserService iBackendUserService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String mlogin() {
        return "backendlogin";
    }

    @RequestMapping(value = "/dologin", method = RequestMethod.POST)
    public String mdoLogin(@RequestParam String userCode,
                           @RequestParam String userPassword,
                           HttpSession session) {
        //待完善，需要查询出用户类型名称
        BackendUser bUser = iBackendUserService.findByNameAndPwd(userCode, userPassword);

        String mav = null;
        if (bUser != null) {
            mav = "backend/main";
            session.setAttribute(Constants.USER_SESSION, bUser);
        } else {
            mav = "redirect:403";
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
        BackendUser bUser = iBackendUserService.findByNameAndPwd(userCode, userPassword);

        if (bUser != null) {
            model.addFlashAttribute("bUser", bUser);
            url = "redirect:backend/main";
        }

        return url;
    }


}
