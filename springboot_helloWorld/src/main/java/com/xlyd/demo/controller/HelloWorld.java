package com.xlyd.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorld {

	@ResponseBody
	@RequestMapping("sayhello")
	public String sayHello() {
		return "spring boot test one";
	}
	
	@ResponseBody
	@RequestMapping("sayHi")
	public String sayHi() {
		return "say hi to spring boot";
	}
	
}
