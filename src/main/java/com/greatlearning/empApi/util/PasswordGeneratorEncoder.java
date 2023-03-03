package com.greatlearning.empApi.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
	public static void main(String[] args) {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println("admin");
		
		System.out.println(passwordEncoder.encode("password"));
		System.out.println(passwordEncoder.encode("$2a$10$24HKtXDtOChJ2ASZSQdr2u9rhEuOGsocUf8i3IPYHWMfTTwFi57a6"));
	}
}
