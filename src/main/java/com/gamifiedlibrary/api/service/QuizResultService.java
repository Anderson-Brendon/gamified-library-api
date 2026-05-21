package com.gamifiedlibrary.api.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.domain.model.QuizResult;
import com.gamifiedlibrary.api.infrastructure.dto.quiz.QuizResultDTO;
import com.gamifiedlibrary.api.infrastructure.dto.quiz.UserAnswerDTO;
import com.gamifiedlibrary.api.repository.QuizResultRepository;

@Service
public class QuizResultService {

	private QuizResultRepository quizResultRepository;
	
	private AppUserService userService;
	
	private BookService bookService;

	public QuizResultService(QuizResultRepository quizResultRepository, AppUserService userService, BookService bookService) {
		this.quizResultRepository = quizResultRepository;
		this.userService = userService;
		this.bookService = bookService;
	}
	
	public List<QuizResult> findAllResultsByUserId(Long userId){
		List<QuizResult> userResults = quizResultRepository.findByUserId(userId);
		return userResults;
	}
	
	public QuizResult findResultsByUserAndBookId(Long userId, Long bookId){
		QuizResult userResult = quizResultRepository.findByUserIdAndBookId(userId, bookId);
		return userResult;
	}
	
	private HashMap<String, Integer> createQuizResult(List<UserAnswerDTO> userAnswers) {
		
		int correctRandomAnswers = 0;
		
		int correctManualAnswers = 0;
		
		int randomAnswers = 0;
		
		int totalPoints = 0;
		
		for(UserAnswerDTO answer : userAnswers) {
			
			if(answer.selectedOption().equals(answer.correctOption())) {
				if(answer.randomSelection()){
					randomAnswers += 1;
					correctRandomAnswers += 1;
				}else {
					correctManualAnswers += 1;
				}
			}
			
		}
		
		totalPoints = correctRandomAnswers * 5 + correctManualAnswers * 10;
		
		HashMap<String, Integer> result = new HashMap<>();
		
		result.put("correctAnswers", correctRandomAnswers + correctManualAnswers);
		result.put("totalPoints", totalPoints);
		result.put("randomAnswers", randomAnswers);
		
		return result;
		
	}
	
	public QuizResultDTO submitQuizResult(Long userId, Long bookId, List<UserAnswerDTO> userAnswers){
		
		HashMap<String, Integer> resultInfo = this.createQuizResult(userAnswers);
		
		AppUser user = userService.findById(userId);
		
		Book book = bookService.findEntityById(bookId);
		
		QuizResult quizResult = new QuizResult(user, book, resultInfo.get("correctAnswers"),resultInfo.get("totalPoints"), resultInfo.get("randomAnswers"));
		
		user.addQuizResult(quizResult);
		
		userService.updateUser(user);
		
		quizResult = user.getQuizResults().getLast();
		
		return new QuizResultDTO(resultInfo.get("randomAnswers"), resultInfo.get("correctAnswers"),resultInfo.get("totalPoints"), quizResult.getCompletedAt());
		
	}

}

/*if(answer.randomSelection() && answer.choosedOption().equals(answer.correctOption())) {
randomAnswers += 1;
correctRandomAnswers += 1;
}else if(answer.randomSelection()){
randomAnswers += 1;
}else {
correctManualAnswers += 1;
}*/