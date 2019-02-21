package com.xlyd.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xlyd.demo.dao.UserDao;

public class SysUserDetailsService implements UserDetailsService {

	private final UserDao userDao;
	
	public SysUserDetailsService(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByUsername(username);
	}

}
