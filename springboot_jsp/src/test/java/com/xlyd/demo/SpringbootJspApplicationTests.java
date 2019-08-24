package com.xlyd.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.xlyd.demo.aop.Performance;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJspApplicationTests {

	@Test
	public void contextLoads() {
	}
	

	@Autowired
	private Performance perf;
	
	@Test
	public void test1() {
		perf.perform();
		System.out.println("------------------------");
		perf.outputString();
		System.out.println("------------------------");
		perf.countNum(111);
		System.out.println("------------------------");
		perf.countBool();
	}
}
