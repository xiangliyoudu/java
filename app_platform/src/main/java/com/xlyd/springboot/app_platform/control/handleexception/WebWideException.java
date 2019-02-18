package com.xlyd.springboot.app_platform.control.handleexception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WebWideException {

    @ExceptionHandler(RuntimeException.class)
    public String fileReadHandle() {
        return "error";
    }
}
