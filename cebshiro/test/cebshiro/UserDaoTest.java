package cebshiro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pojo.User;
import config.RootConfig;
import dao.UserDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={RootConfig.class})
public class UserDaoTest {
	
	
	@Autowired
	UserDao dao;
	
	@Test
	public void test1() {
		User user = dao.findByUsername("admin");
		
		System.out.println(user);
	}
	
	@Test
	public void test2() {
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		dao.save(user);
	}

}













