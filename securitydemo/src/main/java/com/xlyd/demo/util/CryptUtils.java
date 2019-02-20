package com.xlyd.demo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptUtils {

    public static String encodePassword(String rawPassword) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return  encoder.encode(rawPassword);
    }
}
