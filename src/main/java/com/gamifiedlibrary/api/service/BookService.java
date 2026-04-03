package com.gamifiedlibrary.api.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.infrastructure.dto.book.BookDetailDTO;
import com.gamifiedlibrary.api.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
	
	public BookService(BookRepository bookRepository, ReviewService reviewService) {
		this.bookRepository = bookRepository;
		this.reviewService = reviewService;
	}
	
	private BookRepository bookRepository;
	
	private ReviewService reviewService;
	
	public List<Book> findAllBooks(){
		return bookRepository.findAll();
	}
	
	public BookDetailDTO findBookById(Long id){
		System.out.println(bookRepository.findAll().getFirst().getId());
		Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
		Float averageRating = reviewService.findAverageBookRate(id);
		return new BookDetailDTO(book.getId(), book.getTitle(),book.getCover() ,book.getDescription(),
				book.getReleaseYear(), book.getPdf(), book.getAuthor(),
				book.getGenre(), averageRating);
		
	}

	public Book findEntityById(Long bookId){
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("Book not found"));
		return book;
	}
	
	public Page<Book> findPaginated(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
	
	public Book findByTitle(String title) {
		Book book = bookRepository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("No books with similar text found"));
		return book;
	};
	
	//retorna quantidade de paginas baseado em quantos items deseja por pagina
	public HashMap<String, Integer> calculateNumberOfPages(int itemsPerPage) {
		HashMap<String, Integer> result = new HashMap<>();
		result.put("value", Math.round(bookRepository.count() / itemsPerPage + 1));
		return result;
	}
	
	public List<Book> findBooksContainingText(String text) {
		return bookRepository.findTop5ByTitleContainingIgnoreCase(text.trim());
	}
	
}//Pageable pagination = PageRequest.of(Math.round(bookRepository.count() / page), size);
