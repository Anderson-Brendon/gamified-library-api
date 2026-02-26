package com.gamifiedlibrary.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.service.BookService;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

	BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	/*@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = bookService.findAllBooks();
		return ResponseEntity.ok().body(books);
	}*/

	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
		try {
			Book book = this.bookService.findBookById(id);
			return ResponseEntity.ok().body(book);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
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

	@GetMapping("/book/count-pages/{items}")
	public ResponseEntity<Map<String, Integer>> getNumberOfPages(@PathVariable int items)		
	{
		HashMap<String, Integer> result = bookService.calculateNumberOfPages(items);
		return ResponseEntity.ok().body(result);
	}
}
