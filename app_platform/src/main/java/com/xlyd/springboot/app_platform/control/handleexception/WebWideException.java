package com.xlyd.springboot.app_platform.control.handleexception;

import java.io.EOFException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebWideException {
	
	@ExceptionHandler(EOFException.class)
	public String fileReadHandle() {
		return "error";
	}
}
