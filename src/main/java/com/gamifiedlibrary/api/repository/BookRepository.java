package com.gamifiedlibrary.api.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gamifiedlibrary.api.domain.model.Book;

@Repository
public interface BookRepository extends JpaRepository <Book, Long>{
	
	public List<Book> findAll();
	
	public Page<Book> findAll(Pageable pageable);
	
	public Optional<Book> findByTitle(String title);
	
	public Optional<Book> findById(int id);
	
    List<Book> findTop5ByTitleContainingIgnoreCase(String text);
	
	//public Book create(Book book);
	
	//public Book update();
	
	public boolean deleteById(int id);
}
