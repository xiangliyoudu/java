package com.xlyd.demo;

import java.util.Base64;
import java.util.List;

import com.xlyd.demo.dao.RoleDao;
import com.xlyd.demo.entity.Role;
import com.xlyd.demo.util.CryptUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xlyd.demo.dao.UserDao;
import com.xlyd.demo.entity.User;
import com.xlyd.demo.util.LogUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecuritydemoApplicationTests {
	
	Logger log = LogUtils.getLog(SecuritydemoApplicationTests.class);
	
	@Test
	public void contextLoads() {
	}

	@Autowired
	UserDao userDao;
	
	@Test
	public void testUserMapper() {
		List<User> users = userDao.findAll();
		
		log.info("users size: " + users.size());
	}
	
	@Test
	public void testUserFindById() {
		User user = userDao.findById(1);
		log.info(user.toString());
	}

	@Autowired
	RoleDao roleDao;

	@Test
	public void testRoleDao() {
		Role role = roleDao.findById(1);
		log.info(role.toString());
	}

	@Test
	public void testRoleAll(){
		List<Role> roles = roleDao.findAll();
		log.info(roles.toString());
	}

	@Test
	public void testUserByUsername() {
		User user = userDao.findByUsername("admin");
		log.info(user.getUserName());
	}

	@Test
	public void testPasswordEncode(){
		String pwd = "admin";
		pwd = CryptUtils.encodePassword(pwd);
		log.info(pwd.length() + "");
	}
	
}









