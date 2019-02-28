package com.xlyd.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.xlyd.demo.dao.AddressDao;
import com.xlyd.demo.dao.RoleDao;
import com.xlyd.demo.dao.UserDao;
import com.xlyd.demo.entity.Address;
import com.xlyd.demo.entity.Role;
import com.xlyd.demo.entity.User;
import com.xlyd.demo.service.UserService;
import com.xlyd.demo.util.CryptUtils;
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
		log.info(user.getRole().getRoleCode());
	}

	@Test
	public void testPasswordEncode(){
		String pwd = "123";
		pwd = CryptUtils.encodePassword(pwd);
		log.info(pwd);
	}
	
	@Test
	public void testPasswordMatch() {
		String pwd = "123";
		String _pwd = userDao.findByUsername("admin").getUserPassword();
		boolean flag = CryptUtils.getPasswordEncoder().matches(pwd, _pwd);
		log.info(flag + "");
	}
	
	@Autowired
	AddressDao addressDao;
	
	@Test
	public void testAddressByUserId() {
		List<Address> addresses = addressDao.findByUserId(1);
		log.info(addresses.toString());
		Address addr = addressDao.findById(1);
		log.info(addr.getAddressDesc());
	}
	
	
	@Autowired
	UserService userService;
	
	@Autowired
	CacheManager cacheManager;
	
	@Test
	public void testCache() {
		log.info(cacheManager.getCacheNames().toString());
		log.info("------------------------------");
		userService.findAll();
		log.info("------------------------------");
		userService.findAll();
		log.info("------------------------------");
		log.info(cacheManager.getCacheNames().toString());
		log.info(cacheManager.getCache("userCache").get("findAll").get().toString());
	}
	
	@Autowired
	JmsTemplate template;
	
	@Test
	public void testJmsTemplate() {
		// string message
		String msg = "jms test message at: " + new Date();
		
		template.convertAndSend(msg);
		
		// map message
		Map<String, Object> mapMsg = new HashMap<>();
		mapMsg.put("key1", 12345);
		mapMsg.put("key2", new ArrayList<>().add(new Date()));
		
		template.convertAndSend(mapMsg);
		
		// 
			
	}

	
}









