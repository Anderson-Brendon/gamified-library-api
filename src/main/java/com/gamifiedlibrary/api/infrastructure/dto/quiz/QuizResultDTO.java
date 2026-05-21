package com.gamifiedlibrary.api.infrastructure.dto.quiz;

import java.time.OffsetDateTime;

public record QuizResultDTO(int randomAnswers, int correctAnswers, int points, OffsetDateTime completedAt) {

}
