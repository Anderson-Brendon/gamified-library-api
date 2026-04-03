package com.gamifiedlibrary.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.model.Review;
import com.gamifiedlibrary.api.domain.model.ReviewId;

@Repository
public interface ReviewRepository extends JpaRepository<Review, ReviewId> {
	
	public List<Review> findByBookId(Long id);
	
	//public Review findByBookTitle(String title);
	
	@NativeQuery("SELECT COALESCE(AVG(rate), 0) FROM review WHERE book_id = :bookId")
	public Float findAverageBookRate(Long bookId);

	public Optional<Review> findByBookIdAndUserId(Long bookId, Long userId);
}
