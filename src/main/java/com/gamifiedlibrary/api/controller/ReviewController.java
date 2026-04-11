package com.gamifiedlibrary.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gamifiedlibrary.api.domain.model.Review;
import com.gamifiedlibrary.api.infrastructure.utils.JWTService;
import com.gamifiedlibrary.api.service.ReviewService;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    ReviewService reviewService;
   

    public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

    @GetMapping("/{bookId}")
    public ResponseEntity<List<Review>> getReviewsByBookId(@PathVariable Long bookId){
        try {
            List<Review> reviews = this.reviewService.findReviewByBookId(bookId);
            return ResponseEntity.ok().body(reviews);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{bookId}/user/{userId}")
    public ResponseEntity<Review> getReviewByUserIdAndBookId(@PathVariable Long userId, @PathVariable Long bookId){
        
        Review review = this.reviewService.findReviewByUserIdAndBookId(bookId, userId);
        return ResponseEntity.ok().body(review);

    }
    
}
