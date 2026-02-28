package com.gamifiedlibrary.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
	
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	private BookRepository bookRepository;
	
	public List<Book> findAllBooks(){
		return bookRepository.findAll();
	}
	
	public Book findBookById(Long id){
		Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Book not found"));
		return book;
	}
	
	public Page<Book> findPaginated(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}
	
	public Book findByTitle(String title) {
		Book book = bookRepository.findByTitle(title).orElseThrow(() -> new EntityNotFoundException("Book not found"));
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
