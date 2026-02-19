package com.gamifiedlibrary.api.adapter.input;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.application.usecase.AppUserService;
import com.gamifiedlibrary.api.domain.models.AppUser;


@RestController
@RequestMapping("/user")
public class UserControllerRest {
	
	AppUserService appUserCases;
	
	public UserControllerRest(AppUserService appUserCases) {
		this.appUserCases = appUserCases;
	}
	
	@GetMapping
	public ResponseEntity<List<AppUser>> getAllUsers() {
	    List<AppUser> users = appUserCases.findAllUsers();
	    return ResponseEntity.ok().body(users);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AppUser> getById(@PathVariable Long id) {
			 AppUser user = appUserCases.findById(id);
			 return ResponseEntity.ok().body(user);
	}
	
	
}
