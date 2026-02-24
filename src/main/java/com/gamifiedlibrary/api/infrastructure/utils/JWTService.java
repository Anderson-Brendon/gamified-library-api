package com.gamifiedlibrary.api.infrastructure.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {
	
	byte[] bytes = "a_very_strong_and_random_secret_string_of_sufficient_length".getBytes(StandardCharsets.UTF_8);
	
	
	SecretKey key = Keys.hmacShaKeyFor(bytes);
	
	public JWTService() {
		
	}
	
	public String createToken(Long userId, boolean isAdmin) {
		String token = Jwts.builder().claim("id", userId)
		.claim("isAdmin", isAdmin)
		.expiration(new java.util.Date(System.currentTimeMillis() + 604800019)).signWith(key).compact();
		return token;
	}
	
	public boolean checkIfisAdmin(String token) {
		return true;
	}
	
	public boolean isJWTValid(String token) {
		return true;
	}

}
