package com.xlyd.demo;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xlyd.demo.dao.ProductDao;
import com.xlyd.demo.dao.ProductMapper;
import com.xlyd.demo.entity.Product;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootWebTestApplicationTests {
	Logger log = LoggerFactory.getLogger(SpringbootWebTestApplicationTests.class);

	@Test
	public void contextLoads() {
	}

	@Autowired
	ProductDao dao;

	@Autowired
	ProductMapper mapper;

	@Test
	public void testDao() {
		List<Product> ps = dao.findAll();
		System.out.println(ps.size());
	}
	
	@Test
	public void testMapper() {
		List<Product> ps = mapper.findAll();
		System.out.println(ps.get(0).getName());
	}

}




