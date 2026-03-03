package com.gamifiedlibrary.api.service;

import java.util.List;

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
        if(reviews == null){
            throw new EntityNotFoundException();
        }
        return reviews;
    }
    
}
