package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.infrastructure.leaderboard.UserLeaderboardDTO;
import com.gamifiedlibrary.api.repository.QuizResultRepository;

@Service
public class LeaderboardService {
	
	private QuizResultRepository quizResultRepository;

	public LeaderboardService(QuizResultRepository quizResultRepository) {
		this.quizResultRepository = quizResultRepository;
	}
	
	public List<UserLeaderboardDTO> findHighestTotalScores(int size){
		List<UserLeaderboardDTO> highestScoreUsers = quizResultRepository.findUsersWithHighestTotalScore(size);
		return highestScoreUsers;
	}
	
}
