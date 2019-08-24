package com.xlyd.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@RequestMapping("/")
	@ResponseBody
	public String indexPage(Model model) {
		model.addAttribute("name", "zhangsan");
		return "index";
	}
}
