package com.xlyd.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Audience {

	@Pointcut("execution(* com.xlyd.demo.aop.Performance.*(..))")
	private void perfcut() {
	}
	
	@Pointcut("execution(Integer com.xlyd.demo.aop.Performance.countNum(Integer))" + 
			  "&& args(num)")
	private void perfcutwithArgs(Integer num) {
	}
	
	@Before(value="perfcutwithArgs(num)")
	public void numCount(Integer num) {
		System.out.println(">> before num count <<");
		System.out.println(">> num: " + num);
	}

	/*@Before("perfcut()")
	public void silenceCellPhones() {
		System.out.println(">> Silencing the phones <<");
	}

	@Before("perfcut()")
	public void takeSeats() {
		System.out.println(">> Taking Seats <<");
	}

	@AfterReturning("perfcut()")
	public void applause() {
		System.out.println(">> GuaJi GuaJi <<");
	}

	@AfterThrowing("perfcut()")
	public void demandRefund() {
		System.out.println(">> Demanding a refund <<");
	}*/

	@Around("perfcut()")
	public void watchPerform(ProceedingJoinPoint jp) {
		try {
			System.out.println(">> Taking Seats <<");
			System.out.println(">> Silencing the phones <<");
			
			Object _proceed = jp.proceed();
			String _name = _proceed.getClass().getName();
			System.out.println("<-->" + _name);
			
			System.out.println(">> GuaJi GuaJi <<");
		} catch (Throwable e) {
			System.out.println(">> Demanding a refund <<");
		} finally {
			System.out.println(">> Leaving <<");
		}
	}

	
}
