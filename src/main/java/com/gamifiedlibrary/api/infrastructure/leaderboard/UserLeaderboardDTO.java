package com.gamifiedlibrary.api.infrastructure.leaderboard;

public record UserLeaderboardDTO(Long userId, String username, String profilePic, Long totalPoints, Long randomAnswers) {

}
