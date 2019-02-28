package com.xlyd.demo.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.xlyd.demo.entity.User;

public interface UserService {

	@Cacheable(cacheNames = { "userCache" }, key = "#root.methodName")
	List<User> findAll();

	User findById(int id);

	User findByUsername(String userCode);
}
