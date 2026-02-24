package com.gamifiedlibrary.api.repository;

import java.util.List;

import com.gamifiedlibrary.api.domain.model.Review;

public interface ReviewRepository {
	
	public List<Review> findByBookId(int id);
	
	public Review findByBookName(int id);
}
