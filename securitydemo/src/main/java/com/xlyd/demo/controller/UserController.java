package com.xlyd.demo.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.xlyd.demo.entity.User;
import com.xlyd.demo.service.UserService;
import com.xlyd.demo.util.LogUtils;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger log = LogUtils.getLog(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/list")
	public ResponseEntity<?> listUser(UriComponentsBuilder ucb) {

		List<User> users = userService.findAll();
		// set http headers
		HttpHeaders headers = new HttpHeaders();
		URI uri = ucb.path("/user").path("/list").build().toUri();
		headers.setLocation(uri);
		log.info(">>> user list found ...");
		ResponseEntity<?> entity = ResponseEntity.created(uri).headers(headers).body(users);
		return entity;

	}
}
