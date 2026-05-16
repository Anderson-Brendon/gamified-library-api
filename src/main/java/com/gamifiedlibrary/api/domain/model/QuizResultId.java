package com.gamifiedlibrary.api.domain.model;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class QuizResultId implements Serializable {

	private static final long serialVersionUID = 1L;

	public QuizResultId() {
		
	}
	
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
		QuizResultId other = (QuizResultId) obj;
		return bookId == other.bookId && userId == other.userId;
	}
	
	
}
