package com.gamifiedlibrary.api.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamifiedlibrary.api.domain.service.ReadingListBookId;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class ReadingListBook {
	
	public ReadingListBook() {
		
	}
	
    public ReadingListBook(AppUser user, Book book, int currentPage, boolean isComplete) {
		this.book = book;
		this.user = user;
		this.id = new ReadingListBookId(user.getId(), book.getId());
		this.currentPage = currentPage;
		this.isComplete = isComplete;
	}
	
	@EmbeddedId
	private ReadingListBookId id;
	
	@JsonIgnore
	@MapsId("userId")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private AppUser user;
	
	@MapsId("bookId")
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Column(nullable = false, columnDefinition = "integer default 0")
	private int currentPage;
	
	@Column(nullable = false)
	private boolean isComplete;
	
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
}
