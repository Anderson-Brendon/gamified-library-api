package com.gamifiedlibrary.api.infrastructure.utils;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {
	
	@Value("${token.secret-key}")
	public String secretKey;
	
	byte[] bytes;
	
	SecretKey key;
	
	public JWTService(@Value("${token.secret-key}") String secretKey) {
		this.secretKey = secretKey;
		this.bytes = secretKey.getBytes(StandardCharsets.UTF_8);
		this.key = Keys.hmacShaKeyFor(bytes);
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
	
	/*public boolean isJWTValid(String token) {
		try {
			extractAllClaims(token);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}*/
	
	public Claims extractAllClaims(String token) {
	    return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
	            
	}
	
	public String extractBearerToken(String bearerToken) {
		return bearerToken.split(" ")[1];
	}

}
