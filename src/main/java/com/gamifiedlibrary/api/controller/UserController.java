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
import com.gamifiedlibrary.api.infrastructure.dto.book.FavoriteBookDTO;
import com.gamifiedlibrary.api.infrastructure.dto.book.ReadingListBookDTO;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.service.AppUserService;
import com.gamifiedlibrary.api.service.FavoriteBookService;
import com.gamifiedlibrary.api.service.ReadingListService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

	AppUserService appUserServices;

	ReadingListService readingListService;

	FavoriteBookService favoriteBookService;

	public UserController(AppUserService appUserServices, ReadingListService readingListService,
			FavoriteBookService favoriteBookService) {
		this.appUserServices = appUserServices;
		this.readingListService = readingListService;
		this.favoriteBookService = favoriteBookService;
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

	@GetMapping("/reading-list/{userId}")
	public ResponseEntity<List<ReadingListBookDTO>> getUserBooksOnReadingList(@PathVariable Long userId) {
		
		List<ReadingListBookDTO> readingList = this.readingListService.findReadingListByUserId(userId);
		
		return ResponseEntity.ok().body(readingList);
	}

	@GetMapping("/favorites/{userId}")
	public ResponseEntity<List<FavoriteBookDTO>> getUserFavoritesBooks(@PathVariable Long userId) {
		
		List<FavoriteBookDTO> favorites = this.favoriteBookService.FindFavoritesBooksByUserId(userId);
		
		return ResponseEntity.ok().body(favorites);
	}

}

/*
 * @GetMapping("/favorites/{userId}") public ResponseEntity<Set<FavoriteBook>>
 * getUserFavorites(@PathVariable Long userId) { try { AppUser user =
 * appUserServices.findById(userId); return
 * ResponseEntity.ok().body(user.getFavoritesBooks()); } catch (Exception e) {
 * return ResponseEntity.notFound().build(); }
 * 
 * }
 * 
 * @GetMapping("/reading-list/{userId}") public
 * ResponseEntity<Set<ReadingListBook>> getUserReadingList(@PathVariable Long
 * userId) { try { AppUser user = appUserServices.findById(userId); return
 * ResponseEntity.ok().body(user.getBooksOnList()); } catch (Exception e) {
 * return ResponseEntity.notFound().build(); } }
 */
