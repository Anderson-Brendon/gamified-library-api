package com.gamifiedlibrary.api.repository;

import com.gamifiedlibrary.api.domain.model.ReadingListBook;


public interface ReadingListBookRepository {
	
    public ReadingListBook findAll();
	
	public ReadingListBook findById(int id);
	
}
