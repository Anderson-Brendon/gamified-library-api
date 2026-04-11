package com.gamifiedlibrary.api.domain.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable {
	
	public ReviewId() {
		
	}

	public ReviewId(Long userId, Long bookId) {
		this.userId = userId;
		this.bookId = bookId;
	}

	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "book_id")
	private Long bookId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
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
		ReviewId other = (ReviewId) obj;
		return Objects.equals(bookId, other.bookId) && Objects.equals(userId, other.userId);
	}
	
	
}
