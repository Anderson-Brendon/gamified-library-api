package com.gamifiedlibrary.api.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.infrastructure.dto.appuser.LoginDTO;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.service.AuthenticationService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	AuthenticationService authService;
	
	public AuthenticationController(AuthenticationService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
				String token = authService.login(loginDTO.username(), loginDTO.password());
				if(token == null) {
					return ResponseEntity.status(401)
							.body(CustomAPIMessage.setMessage("exception", "Login attempt failed"));
				}
				return ResponseEntity.ok().body(CustomAPIMessage.setMessage("token", token));
	}
}