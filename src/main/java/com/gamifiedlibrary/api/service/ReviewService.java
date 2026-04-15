package com.gamifiedlibrary.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.domain.model.Review;
import com.gamifiedlibrary.api.repository.BookRepository;
import com.gamifiedlibrary.api.repository.ReviewRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;

	private AppUserService userService;

	private BookRepository bookRepository;

	public ReviewService(ReviewRepository reviewRepository, AppUserService userService, BookRepository bookRepository) {
		this.reviewRepository = reviewRepository;
		this.userService = userService;
		this.bookRepository = bookRepository;
	}

	public List<Review> findReviewByBookId(Long bookId) {
		List<Review> reviews = this.reviewRepository.findByBookId(bookId);
		return reviews;
	}

	public float findAverageBookRate(Long bookId) {
		return this.reviewRepository.findAverageBookRate(bookId);
	}

	public Review findReviewByUserIdAndBookId(Long bookId, Long userId) {
		Optional<Review> review = this.reviewRepository.findByBookIdAndUserId(bookId, userId);
		if (review.isEmpty()) {
			throw new EntityNotFoundException(
					"Review not found.");
		}
		return review.get();
	}

	public void addReview(Long userId, Long bookId, int rate, String comment) {
		AppUser user = userService.findById(userId);
		
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new EntityNotFoundException("Book not found and the review can't be created"));

		user.addReview(book, rate, comment);
		
		userService.updateUser(user);

	}
	
	public void updateReview(Long userId, Long bookId, int rate, String comment) {
		
		AppUser user = userService.findById(userId);

		user.editReview(bookId, rate, comment);
		
		userService.updateUser(user);

	}

}
