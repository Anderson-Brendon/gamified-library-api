package com.gamifiedlibrary.api.domain.repository;

import java.util.List;

import com.gamifiedlibrary.api.domain.models.Review;

public interface ReviewRepository {
	
	public List<Review> findByBookId(int id);
	
	public Review findByBookName(int id);
}
