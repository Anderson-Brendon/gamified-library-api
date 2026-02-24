package com.gamifiedlibrary.api.infrastructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordService {
	
	static public String createPasswordHash(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode(password);
		return result;
	}
	
	static public boolean isPasswordCorrect(String password, String hashedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder.matches(password, hashedPassword);
	}
}
