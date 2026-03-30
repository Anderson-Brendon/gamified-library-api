package com.gamifiedlibrary.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.infrastructure.dto.FavoriteBookCheckDTO;
import com.gamifiedlibrary.api.service.FavoriteBookService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/favorite-books")
public class FavoriteBookController {
	FavoriteBookService favoriteBookService;
	
	public FavoriteBookController(FavoriteBookService favoriteBookService) {
		this.favoriteBookService = favoriteBookService;
	}
	
	@GetMapping("/{userId}/contains/{bookId}")
	public ResponseEntity<FavoriteBookCheckDTO> checkIfBookIsAUserFavorite(@PathVariable Long userId, @PathVariable Long bookId){
		boolean exists = favoriteBookService.isBookOnUserFavorites(bookId, userId);
		System.out.println("existe " + exists);
		return ResponseEntity.ok().body(new FavoriteBookCheckDTO(exists));
	}
}
