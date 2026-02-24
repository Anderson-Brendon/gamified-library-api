package com.gamifiedlibrary.api.repository;

import com.gamifiedlibrary.api.domain.model.QuizQuestion;

public interface QuizQuestionRepository {
	
	public QuizQuestion findAll();
	
	public QuizQuestion findById(int id);
	
	public QuizQuestion create(QuizQuestionDTO quizQuestionDTO);
	
	public QuizQuestion update(int id, QuizQuestionDTO quizQuestionDTO);
	
	public QuizQuestion deleteById(int id);
	
}
