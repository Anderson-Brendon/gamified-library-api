package com.gamifiedlibrary.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.Review;
import com.gamifiedlibrary.api.repository.ReviewRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    public List<Review> findReviewByBookId(Long bookId){
        List<Review> reviews = this.reviewRepository.findByBookId(bookId);
        return reviews;
    }
    
    public float findAverageBookRate(Long bookId){
    	return this.reviewRepository.findAverageBookRate(bookId);
    }

    public Review findReviewByUserIdAndBookId(Long bookId, Long userId){
        Optional<Review> review = this.reviewRepository.findByBookIdAndUserId(bookId, userId);
        if(review.isEmpty()){
            throw new EntityNotFoundException("Review by user with id " + userId + " for book with id " + bookId + " not found.");
        }
        return review.get();
    }
    
}
