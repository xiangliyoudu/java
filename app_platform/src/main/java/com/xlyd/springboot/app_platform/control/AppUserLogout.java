package com.xlyd.springboot.app_platform.control;

import javax.servlet.http.HttpSession;

import com.xlyd.springboot.app_platform.entity.BackendUser;
import com.xlyd.springboot.app_platform.entity.DevUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xlyd.springboot.app_platform.tool.Constants;

@Controller
public class AppUserLogout {

    @RequestMapping(value = {"/dev/logout", "/manager/logout"})
    public String logout(HttpSession session) {
        BackendUser backendUser = (BackendUser) session.getAttribute(Constants.BACKEND_USER_SESSION);
        if (backendUser != null) {
            session.removeAttribute(Constants.BACKEND_USER_SESSION);
        }

        DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
        if (devUser != null) {
            session.removeAttribute(Constants.DEV_USER_SESSION);
        }

        return "redirect:/index";
    }
}
