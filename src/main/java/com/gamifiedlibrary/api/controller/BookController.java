package com.gamifiedlibrary.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.domain.model.QuizQuestion;
import com.gamifiedlibrary.api.infrastructure.dto.ReviewPostingDTO;
import com.gamifiedlibrary.api.infrastructure.dto.book.BookDetailDTO;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.infrastructure.utils.JWTService;
import com.gamifiedlibrary.api.service.BookService;
import com.gamifiedlibrary.api.service.QuizQuestionService;
import com.gamifiedlibrary.api.service.ReviewService;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	BookService bookService;
	
	private JWTService jwtService;
	
	private ReviewService reviewService;
	
	private QuizQuestionService questionService;
	
	public BookController(BookService bookService, JWTService jwtService, ReviewService reviewService, QuizQuestionService questionService) {
		this.bookService = bookService;
		this.jwtService = jwtService;
		this.reviewService = reviewService;
		this.questionService = questionService;
	}

	/*@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = bookService.findAllBooks();
		return ResponseEntity.ok().body(books);
	}*/

	@GetMapping("/{id}")
	public ResponseEntity<BookDetailDTO> getBookById(@PathVariable Long id){
			BookDetailDTO book = this.bookService.findBookById(id);
			return ResponseEntity.ok().body(book);
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getPaginatedBooks(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "true") boolean ascending
			)
	{
		Pageable pageable = PageRequest.of(page, size);
		Page<Book> books = bookService.findPaginated(pageable);
		return ResponseEntity.ok().body(books);
	}

	@GetMapping("/like/{text}")
	public ResponseEntity<List<Book>> getBooksWithSimilarText(@PathVariable String text)		
	{
		List<Book> books = bookService.findBooksContainingText(text);
		return ResponseEntity.ok().body(books);
	}
	
	@PostMapping("/{bookId}/review")
    public ResponseEntity<Map<String, String>> postUserReview(@RequestHeader(value = "Authorization", required = false) String authorizationHeader, @RequestBody ReviewPostingDTO reviewDTO, @PathVariable Long bookId) {
    	
		String token;
		Long userId;
		
		token = jwtService.extractBearerToken(authorizationHeader);	
		
		userId = jwtService.extractAllClaims(token).get("id", Long.class);
		
		System.out.println(reviewDTO.comment());
		
        reviewService.addReview(userId , bookId, reviewDTO.rate(), reviewDTO.comment());
        
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomAPIMessage.setMessage("success", "Review from user with id " + userId + " was added for book with id " + bookId));
        
    }
	
	@GetMapping("/{id}/quiz-questions")
	public ResponseEntity<List<QuizQuestion>> findQuizQuestionsFromBook(@PathVariable Long id){
			List<QuizQuestion> questions = questionService.findAllQuestionsFromBook(id);
			return ResponseEntity.ok().body(questions);
	}

	
}
/*@GetMapping("/book/{}")
	public ResponseEntity<Map<String, Integer>> getNumberOfPages(@PathVariable int items)		
	{
		HashMap<String, Integer> result = bookService.calculateNumberOfPages(items);
		return ResponseEntity.ok().body(result);
	}*/