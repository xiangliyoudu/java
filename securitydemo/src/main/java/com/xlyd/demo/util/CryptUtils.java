package com.xlyd.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CryptUtils {

	public static PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static String encodePassword(String rawPassword) {
		return getPasswordEncoder().encode(rawPassword);
	}
}
