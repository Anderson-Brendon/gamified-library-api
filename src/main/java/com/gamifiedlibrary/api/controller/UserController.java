package com.gamifiedlibrary.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.AccountCreationDTO;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.service.AppUserService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
	
	AppUserService appUserServices;
	
	public UserController(AppUserService appUserServices) {
		this.appUserServices = appUserServices;
	}
	
	@GetMapping
	public ResponseEntity<List<AppUser>> getAllUsers() {
	    List<AppUser> users = appUserServices.findAllUsers();
	    return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUser> getById(@PathVariable Long id) {
			 AppUser user = appUserServices.findById(id);
			 return ResponseEntity.ok().body(user);
	}

	@PostMapping
	public ResponseEntity<Map<String, String>> createUser(@RequestBody AccountCreationDTO accountCreationDTO) {
		try {
			appUserServices.createUser(accountCreationDTO);
			return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Account created"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(CustomAPIMessage.setMessage("exception", e.getMessage()));
		}
		
	}
	
	
}
