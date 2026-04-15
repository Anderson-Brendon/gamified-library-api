package com.gamifiedlibrary.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Review {
	
	public Review() {
		
	}
	
    public Review(AppUser user, Book book, int rate, String comment) {
		this.user = user;
		this.book = book;
		this.rate = rate;
		this.comment = comment;
		this.id = new ReviewId(user.getId(), book.getId());
	}
	
	@EmbeddedId
	private ReviewId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private AppUser user;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("bookId")
	@JoinColumn(name = "book_id")
	private Book book;
	
	//min-max para inteiros
	@Min(value = 1, message = "The book rate must be between 1 and 5.")
	@Max(value = 5, message = "The book rate must be between 1 and 5.")
	@NotNull
	private int rate;
    
	//size para comprimento da string
	@NotBlank
	private String comment;

	public void updateReview(int rate, String comment) {
		this.setRate(rate);
		this.setComment(comment);
	}
	
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		if(rate < 1) {
			throw new IllegalArgumentException("Book rate can't be smaller than 1");
		}
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		if(comment.length() == 0) {
			throw new IllegalArgumentException("Comment should have at least a single character");
		}
		this.comment = comment;
	}

    public ReviewId getId() {
        return id;
    }

    public void setId(ReviewId id) {
        this.id = id;
    }
	
}
