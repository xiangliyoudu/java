package cn.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	//index
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String welcome() {
		return "index";
	}
}
