package com.gamifiedlibrary.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.domain.model.QuizResult;
import com.gamifiedlibrary.api.service.QuizResultService;

@RestController
@RequestMapping("/quiz-results")
public class QuizResultController {

	public QuizResultService quizResultService;

	public QuizResultController(QuizResultService quizResultService) {
		this.quizResultService = quizResultService;
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<QuizResult>> getQuizResultsByUserId(@PathVariable Long userId){
		List<QuizResult> results = quizResultService.findAllResultsByUserById(userId);
		return ResponseEntity.ok(results);
	}
	
}
