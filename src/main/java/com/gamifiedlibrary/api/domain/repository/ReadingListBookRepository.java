package com.gamifiedlibrary.api.domain.repository;

import com.gamifiedlibrary.api.domain.models.ReadingListBook;


public interface ReadingListBookRepository {
	
    public ReadingListBook findAll();
	
	public ReadingListBook findById(int id);
	
}
