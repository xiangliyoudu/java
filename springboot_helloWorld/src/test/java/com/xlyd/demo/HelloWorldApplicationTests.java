package com.xlyd.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xlyd.demo.entity.Student;
import com.xlyd.demo.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(HelloWorldApplicationTests.class);
	
	@Autowired
	Student stu;
	
	@Autowired
	StudentService service;
	
	@Test
	public void contextLoads() {
		System.out.println(service.ss);
	}
	
	@Test
	public void logTest() {
		logger.trace("trace....");
		logger.debug("debug.....");
		logger.info("log test");
	}

}





