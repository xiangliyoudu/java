package com.xlyd.demo.aop;

import org.springframework.stereotype.Component;

@Component
public class Performance {
	
	
	public void perform() {
		System.out.println("performing .....");
	}
	
	public String outputString() {
		System.out.println("output string ......");
		return "string";
	}
	
	public Integer countNum(Integer num) {
		System.out.println("count num .......");
		return 33;
	}
	
	public Boolean countBool() {
		System.out.println("count boolean ......");
		return false;
	}
}
