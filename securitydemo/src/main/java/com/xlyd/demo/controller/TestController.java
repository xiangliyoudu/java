package com.xlyd.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlyd.demo.util.LogUtils;


@Controller
@RequestMapping("/test")
public class TestController {
	
	private static Logger log = LogUtils.getLog(TestController.class);
	
	@ResponseBody
	@RequestMapping("/1")
	public Object Test1() {
		Map<String, Object> result = new HashMap<>();
		List<Integer> strs = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			strs.add(i);
		}
		
		result.put("list", strs);
		result.put("time for now", new Date());
		
		log.info("resut for test1");
		return result;
	}
}
