package com.gamifiedlibrary.api.repository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
	
	public List<Book> findAll();
	
	public Optional<Book> findById(int id);
	
	public Book create(Book book);
	
	public Book update(BookDTO bookDTO);
	
	public boolean deleteById(int id);
}
