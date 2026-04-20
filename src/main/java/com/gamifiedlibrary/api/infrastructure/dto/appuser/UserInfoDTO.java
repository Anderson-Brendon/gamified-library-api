package com.gamifiedlibrary.api.infrastructure.dto.appuser;

public record UserInfoDTO(String userName, String profilePic, String email, int totalQuizPoints,int totalRandomAnswersChoosed,int totalCorrectAnswers, int completedQuizzes) {

}
