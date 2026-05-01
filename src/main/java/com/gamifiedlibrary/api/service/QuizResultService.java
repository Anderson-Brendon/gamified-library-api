package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.QuizResult;
import com.gamifiedlibrary.api.repository.QuizResultRepository;

@Service
public class QuizResultService {

	private QuizResultRepository quizResultRepository;

	public QuizResultService(QuizResultRepository quizResultRepository) {
		this.quizResultRepository = quizResultRepository;
	}
	
	public List<QuizResult> findAllResultsByUserId(Long userId){
		List<QuizResult> userResults = quizResultRepository.findByUserId(userId);
		return userResults;
	}
	
	public QuizResult findResultsByUserAndBookId(Long userId, Long bookId){
		QuizResult userResult = quizResultRepository.findByUserIdAndBookId(userId, bookId);
		return userResult;
	}
	
}
