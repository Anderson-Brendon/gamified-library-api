package com.gamifiedlibrary.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gamifiedlibrary.api.infrastructure.dto.leaderboard.UserLeaderboardDTO;
import com.gamifiedlibrary.api.service.LeaderboardService;


@RestController
@RequestMapping("/leaderboard")
public class LeaderboardController {

	public LeaderboardService leaderboardService;

	public LeaderboardController(LeaderboardService leaderboardService) {
		this.leaderboardService = leaderboardService;
	}
	
	@GetMapping
	public ResponseEntity<List<UserLeaderboardDTO>> getLeaderboard(@RequestParam(defaultValue = "5") int size){
		List<UserLeaderboardDTO> leaders = leaderboardService.findHighestTotalScores(size);
		return ResponseEntity.ok(leaders);
	}
	

}
	
