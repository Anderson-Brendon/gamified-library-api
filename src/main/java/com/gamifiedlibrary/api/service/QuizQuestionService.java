package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.QuizQuestion;
import com.gamifiedlibrary.api.repository.QuizQuestionRepository;

@Service
public class QuizQuestionService {
	
	
	private QuizQuestionRepository questionRepository;
	
	public QuizQuestionService(QuizQuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}
	
	public List<QuizQuestion> findAllQuestionsFromBook(Long id) {
      return questionRepository.findAllByBookId(id);
	}
}
