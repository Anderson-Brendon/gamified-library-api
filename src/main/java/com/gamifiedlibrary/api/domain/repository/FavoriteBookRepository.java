package com.gamifiedlibrary.api.domain.repository;

import java.util.List;
import com.gamifiedlibrary.api.domain.models.FavoriteBook;

public interface FavoriteBookRepository {
	
    public List<FavoriteBook> findByUserId();
    
    public List<FavoriteBook> findByMostFavorites();
	
	/*public Optional<FavoriteBook> findById(int userId, int bookId);
	
	public FavoriteBook create (AppUser user, Book book);
	
	public Book update(int userId, int bookId);
	
	public boolean delete(int id);*/
}
