package com.gamifiedlibrary.api.service;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.infrastructure.utils.JWTService;
import com.gamifiedlibrary.api.infrastructure.utils.PasswordService;

@Service
public class AuthenticationService {
	
	public AuthenticationService(AppUserService userService, JWTService jwtService) {
		this.userService = userService;
		this.jwtService = jwtService;
	}
	
	AppUserService userService;
	
	JWTService jwtService;
	
	public String login(String email, String password) {
		AppUser user = userService.findByUsername(email);
		if(PasswordService.isPasswordCorrect(password, user.getPasswordHash())) {
			String tokenJWT = jwtService.createToken(user.getId(), user.isAdmin());
			return tokenJWT;
		}else{
			return null;
		}
	}
}
