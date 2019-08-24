package com.xlyd.vue.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xlyd.vue.entity.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping("/")
	public Object getAllUsers() {
		String name = "user-";
		String position = "pos-";
		boolean show = false;
		
		List<User> users = new ArrayList<User>();
		User user = null;
		for(int i=1;i<5;i++) {
			user = new User(name + i, position + i * i, show);
			users.add(user);
		}
		
		return users;
	}
	
	@Autowired
	private User u;
	
	@GetMapping("/{id}")
	public Object getById() {
		return u;
	}
}













