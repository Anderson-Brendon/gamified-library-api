package com.gamifiedlibrary.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
    public ReadingListBook(AppUser user, Book book, int currentPage, boolean complete) {
		this.book = book;
		this.user = user;
		this.id = new ReadingListBookId(user.getId(), book.getId());
		this.currentPage = currentPage;
		this.complete = complete;
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
	private boolean complete;
	
	public boolean getIsComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
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

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
