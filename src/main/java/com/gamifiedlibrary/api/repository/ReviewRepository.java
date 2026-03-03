package com.gamifiedlibrary.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.model.Review;
import com.gamifiedlibrary.api.domain.model.ReviewId;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
	
	public List<Review> findByBookId(Long id);
	
	//public Review findByBookTitle(String title);
}
