package com.gamifiedlibrary.api.domain.service;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReadingListBookId implements Serializable{

	public ReadingListBookId() {
		
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "book_id")
	private int bookId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bookId, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReadingListBookId other = (ReadingListBookId) obj;
		return bookId == other.bookId && userId == other.userId;
	}
	
	
	
}
