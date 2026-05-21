package com.gamifiedlibrary.api.domain.model;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;

@Entity
public class QuizResult {
	
	public QuizResult() {
		
	}
	
	public QuizResult(AppUser user, Book book, int correctAnswers, int points, int randomAnswers) {
		this.user = user;
		this.book = book;
		this.correctAnswers = correctAnswers;
		this.points = points;
		this.randomAnswers = randomAnswers;
	}

	@EmbeddedId
	private QuizResultId id = new QuizResultId();;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	@JoinColumn(name = "user_id")//apenas o lado dono da relação deve usar o joincolumn
	private AppUser user;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("bookId")
	@JoinColumn(name = "book_id")
	private Book book;
	
	@Max(value = 10, message = "Value must be less than or equal to 10")
	@NotNull
	private int correctAnswers;
	
	@Max(value = 100, message = "Value must be less than or equal to 100")
	@NotNull
	private int points;
	
	@Max(value = 10, message = "Value must be less than or equal to 10")
	@NotNull
	private int randomAnswers;

	@CreationTimestamp
	@Column(name = "completed_at", insertable = false, updatable = false)
	private OffsetDateTime completedAt;

	public QuizResultId getId() {
		return id;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getRandomAnswers() {
		return randomAnswers;
	}

	public void setRandomAnswers(int randonAnswersChoosed) {
		this.randomAnswers = randonAnswersChoosed;
	}

	public OffsetDateTime getCompletedAt() {
		return completedAt;
	}

}
