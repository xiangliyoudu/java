package com.xlyd.demo.service;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xlyd.demo.dao.UserDao;
import com.xlyd.demo.entity.User;
import com.xlyd.demo.util.LogUtils;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LogUtils.getLog(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> findAll() {
		log.info("UserDao method: findAll invocated ...");
		return userDao.findAll();
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	@Override
	public User findByUsername(String userCode) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(userCode);
	}

}
