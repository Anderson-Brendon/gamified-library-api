package com.gamifiedlibrary.api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.infrastructure.utils.JWTService;
import com.gamifiedlibrary.api.service.AppUserService;
import com.gamifiedlibrary.api.service.StorageService;


@CrossOrigin("*")
@RestController
@RequestMapping("/storage")
public class StorageController {

    StorageService storageService;
    
    JWTService jwtService;
    
    AppUserService appUserService;
    
    String r2Url = "https://pub-8e2d3a98b52c474a812811106fb89cf4.r2.dev/";

    public StorageController(StorageService storageService, JWTService jwtService, AppUserService appUserService) {
		this.storageService = storageService;
		this.jwtService = jwtService;
		this.appUserService = appUserService;
	}


	@GetMapping("/profile-pic/presigned-url")
    public ResponseEntity<Map<String, String>> getUrlToPutProfilePic(@RequestHeader(value = "Authorization", required = false) String authorizationHeader){
    	
		try {
    		String token = jwtService.extractBearerToken(authorizationHeader);	
    		Long id = jwtService.extractAllClaims(token).get("id", Long.class);
    		AppUser user = appUserService.findById(id);
    		String presignedUrl = storageService.createPresignedUrlToPut("profile-pic/", user.getUsername());
    		user.setProfilePic(r2Url + "profile-pic/" + user.getUsername());
    		appUserService.updateUser(user);
            return ResponseEntity.ok().body(CustomAPIMessage.setMessage("presignedUrl", presignedUrl));
    	}
    	catch(Exception e) {
    		 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
    				 .body(CustomAPIMessage.setMessage("exception", "Unauthorized"));
    	}
    	
        
    }
}
