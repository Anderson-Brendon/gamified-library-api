package com.gamifiedlibrary.api.domain.models;

import com.gamifiedlibrary.api.domain.service.ReviewId;
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
import jakarta.validation.constraints.Size;

@Entity
public class Review {
	
	public Review() {
		
	}
	
    public Review(AppUser user, Book book, int rate, String comment) {
		this.user = user;
		this.book = book;
		this.rate = rate;
		this.comment = comment;
	}
	
	@EmbeddedId
	private ReviewId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	private AppUser user;
	
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
	@Size(min = 6, max = 15, message = "Name must be between 6 and 15 characters long.")
	@NotBlank
	private String comment;

	public void update(int rate, String comment) {
		this.rate = rate;
		this.rate = rate;
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
		this.rate = rate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
