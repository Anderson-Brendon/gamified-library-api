package com.gamifiedlibrary.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.model.FavoriteBook;
import com.gamifiedlibrary.api.domain.model.FavoriteBookId;

@Repository
public interface FavoriteBookRepository extends JpaRepository<FavoriteBook, FavoriteBookId>{
	
    public List<FavoriteBook> findByUserId(Long userId);
	
	/*public Optional<FavoriteBook> findById(int userId, int bookId);
	
	public FavoriteBook create (AppUser user, Book book);
	
	public Book update(int userId, int bookId);
	
	public boolean delete(int id);*/
}
