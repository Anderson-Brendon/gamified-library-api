package com.gamifiedlibrary.api.infrastructure.dto.leaderboard;

public record UserLeaderboardDTO(Long userId, String username, String profilePic, Long totalPoints, Long randomAnswers) {

}
