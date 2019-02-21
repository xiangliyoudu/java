package com.xlyd.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlyd.demo.dao.UserDao;
import com.xlyd.demo.entity.User;
import com.xlyd.demo.util.LogUtils;

@RestController
@RequestMapping("/do")
public class IndexController {
	
	private Logger log = LogUtils.getLog(IndexController.class);

	@Autowired
	private UserDao userDao;
	
	@GetMapping("/1")
	public Object first() {
		List<User> us = userDao.findAll();
		
		log.info("user size: " + us.size());
		return us;
	}
	
}
