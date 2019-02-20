package com.xlyd.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlyd.demo.util.LogUtils;

@RestController
public class IndexController {
	
	Logger log = LogUtils.getLog(IndexController.class);

	@GetMapping("/test1")
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
