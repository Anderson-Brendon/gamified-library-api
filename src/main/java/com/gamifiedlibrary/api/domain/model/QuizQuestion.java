package com.gamifiedlibrary.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class QuizQuestion {
	
	public QuizQuestion() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id")
	private Book book;
	
	@NotBlank
	private String questionText;
	
	@NotBlank
	@Column(name = "option_a")
	private String optionA;
	
	@NotBlank
	@Column(name = "option_b")
	private String optionB;
	
	@NotBlank
	@Column(name = "option_c")
	private String optionC;
	
	@NotBlank
	@Column(name = "option_d")
	private String optionD;
	
	@NotBlank
	private char correctOption;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public char getCorrectOption() {
		return correctOption;
	}

	public void setCorrectOption(char correctOption) {
		this.correctOption = correctOption;
	}
	
	
}
