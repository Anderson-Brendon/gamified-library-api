package com.gamifiedlibrary.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.domain.model.FavoriteBook;
import com.gamifiedlibrary.api.domain.model.ReadingListBook;
import com.gamifiedlibrary.api.infrastructure.dto.ReviewPostingDTO;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.AccountCreationDTO;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.UserInfoDTO;
import com.gamifiedlibrary.api.infrastructure.dto.book.FavoriteBookDTO;
import com.gamifiedlibrary.api.infrastructure.dto.book.ReadingListBookDTO;
import com.gamifiedlibrary.api.infrastructure.dto.book.ReadingListUpdateDTO;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.infrastructure.utils.JWTService;
import com.gamifiedlibrary.api.service.AppUserService;
import com.gamifiedlibrary.api.service.FavoriteBookService;
import com.gamifiedlibrary.api.service.ReadingListService;
import com.gamifiedlibrary.api.service.ReviewService;

import software.amazon.awssdk.http.HttpStatusCode;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final ReviewService reviewService;

	AppUserService appUserServices;

	ReadingListService readingListService;

	FavoriteBookService favoriteBookService;
	
	JWTService jwtService;

	public UserController(AppUserService appUserServices, ReadingListService readingListService,
			FavoriteBookService favoriteBookService, JWTService jwtService, ReviewService reviewService) {
		this.appUserServices = appUserServices;
		this.readingListService = readingListService;
		this.favoriteBookService = favoriteBookService;
		this.jwtService = jwtService;
		this.reviewService = reviewService;
	}

	@GetMapping
	public ResponseEntity<List<AppUser>> getAllUsers() {
		List<AppUser> users = appUserServices.findAllUsers();
		return ResponseEntity.ok().body(users);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserInfoDTO> getUserById(@PathVariable Long userId) {
		UserInfoDTO user = appUserServices.getUserInfo(userId);
		return ResponseEntity.ok().body(user);
	}
	
	/*@GetMapping("/{userId}/stats")
	public ResponseEntity<UserInfoDTO> getUserByStats(@PathVariable Long userId) {
		UserInfoDTO user = appUserServices.getUserDetails(userId);
		return ResponseEntity.ok().body(user);
	}*/

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
	public ResponseEntity<List<ReadingListBookDTO>> getAllUserBooksOnReadingList(@PathVariable Long userId, @RequestParam(required = false) Boolean completed) {
		List<ReadingListBookDTO> readingList;

		if(completed == null){
			readingList = this.readingListService.findReadingListByUserId(userId);
		}else if(completed){
			readingList = this.readingListService.findBooksFromReadingListCompleted(userId);
		}else{
			readingList = this.readingListService.findBooksFromReadingListUnfinished(userId);
		}
		
		return ResponseEntity.ok().body(readingList);
	}

	@DeleteMapping("/reading-list/{bookId}")
	public ResponseEntity<Map<String, String>> deleteUserBookOnReadingList(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable Long bookId) {
		
		String token;
		Long id;
		try {
			token = jwtService.extractBearerToken(authorizationHeader);	
			id = jwtService.extractAllClaims(token).get("id", Long.class);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
   				 .body(CustomAPIMessage.setMessage("exception", "Unauthorized"));
		}
		
		AppUser user = this.appUserServices.findById(id);
		
		Optional<ReadingListBook> optionalBook = user.getBooksOnList().stream()
		.filter((book -> Objects.equals(book.getBook().getId(), bookId))).
		findFirst();

		Boolean isCompleted = optionalBook.get().getIsComplete();

		if(isCompleted){
			return ResponseEntity.badRequest().body(CustomAPIMessage.setMessage("exception", "Book marked as complete can't be removed"));
		}
		
		if(optionalBook.isPresent()) {
			user.removeBookFromList(optionalBook.get().getBook());
			appUserServices.updateUser(user);
			return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Book removed from reading list"));
		}
		
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/favorites/{userId}")
	public ResponseEntity<List<FavoriteBookDTO>> getUserFavoriteBooks(@PathVariable Long userId) {
		
		List<FavoriteBookDTO> favorites = this.favoriteBookService.FindFavoritesBooksByUserId(userId);
		
		return ResponseEntity.ok().body(favorites);
	}
	
	@DeleteMapping("/favorites/{bookId}")
	public ResponseEntity<Map<String, String>> deleteUserFavoriteBook(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @PathVariable Long bookId) {
		
		String token;
		Long id;
		try {
			token = jwtService.extractBearerToken(authorizationHeader);	
			id = jwtService.extractAllClaims(token).get("id", Long.class);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
   				 .body(CustomAPIMessage.setMessage("exception", "Unauthorized"));
		}
		
		AppUser user = this.appUserServices.findById(id);
		
		Optional<FavoriteBook> optionalBook = user.getFavoritesBooks().stream().filter((book -> book.getBook().getId() == bookId)).
		findFirst();
		
		if(optionalBook.isPresent()) {
			user.removeBookFromFavorite(optionalBook.get().getBook());
			appUserServices.updateUser(user);
			return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Book removed from favorites"));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PatchMapping("/reading-list/{bookId}")
	public ResponseEntity<Map<String, String>> updateBookFromReadingList(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable Long bookId, @RequestBody ReadingListUpdateDTO dto) {
		
		String token;
		
		Long id;
		
		try {
			token = jwtService.extractBearerToken(authorizationHeader);	
			id = jwtService.extractAllClaims(token).get("id", Long.class);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
   				 .body(CustomAPIMessage.setMessage("exception", "Unauthorized"));
		}
		
		try {
			readingListService.updateBookFromReadingList(id, bookId, dto);
			return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Book from list was updated"));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}
	
	@PostMapping("/reading-list/{bookId}")
	public ResponseEntity<Map<String, String>> saveBookOnReadingList(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable Long bookId) {
		String token;
		
		Long id;
		
		try {
			token = jwtService.extractBearerToken(authorizationHeader);	
			id = jwtService.extractAllClaims(token).get("id", Long.class);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
   				 .body(CustomAPIMessage.setMessage("exception", "Unauthorized"));
		}

		try {
			readingListService.addBookToUserReadingList(id, bookId);
			return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Book added to user list"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(CustomAPIMessage.setMessage("exception", e.getMessage()));
		}

	}
	
	@PostMapping("/favorites/{bookId}")
	public ResponseEntity<Map<String, String>> saveBookOnFavorites(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable Long bookId) {
		String token;
		
		Long id;
		
		try {
			token = jwtService.extractBearerToken(authorizationHeader);	
			id = jwtService.extractAllClaims(token).get("id", Long.class);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
   				 .body(CustomAPIMessage.setMessage("exception", "Unauthorized"));
		}

		try {
			favoriteBookService.addBookToUserFavorites(id, bookId);
			return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Book added to user list"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body(CustomAPIMessage.setMessage("exception", e.getMessage()));
		}

	}
	
	@PostMapping("/reviews/{bookId}")
	public ResponseEntity<Map<String, String>> addUserReview(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable Long bookId, @RequestBody ReviewPostingDTO review) {
		String token;
		
		Long userId;
		
		token = jwtService.extractBearerToken(authorizationHeader);	
		userId = jwtService.extractAllClaims(token).get("id", Long.class);
		
		reviewService.addReview(userId, bookId, review.rate(), review.comment());
		
		return ResponseEntity.status(HttpStatusCode.CREATED).body(CustomAPIMessage.setMessage("success", "Review was created"));
		
	}
	
	@PatchMapping("/reviews/{bookId}")
	public ResponseEntity<Map<String, String>> updateUserReview(@RequestHeader(value = "Authorization", required = false) String authorizationHeader , @PathVariable Long bookId, @RequestBody ReviewPostingDTO review) {
		String token;
		
		Long userId;
		
		System.out.println(review.rate());
		
		token = jwtService.extractBearerToken(authorizationHeader);	
		userId = jwtService.extractAllClaims(token).get("id", Long.class);
		
		reviewService.updateReview(userId, bookId, review.rate(), review.comment());
		
		return ResponseEntity.ok().body(CustomAPIMessage.setMessage("success", "Review was created"));
		
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
