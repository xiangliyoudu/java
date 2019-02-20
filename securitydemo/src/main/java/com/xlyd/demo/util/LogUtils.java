package com.xlyd.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
	
	public static Logger getLog(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
}
