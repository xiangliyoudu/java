package com.xlyd.demo.util;

import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CryptUtils {

	public static PasswordEncoder getPasswordEncoder() {
		SecureRandom random = new SecureRandom();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10, random);
		return encoder;
	}

	public static String encodePassword(String rawPassword) {
		return getPasswordEncoder().encode(rawPassword);
	}
}
