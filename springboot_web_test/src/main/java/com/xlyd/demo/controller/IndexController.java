package com.xlyd.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	
	
}
