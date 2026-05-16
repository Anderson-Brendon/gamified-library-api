package com.gamifiedlibrary.api.infrastructure.dto.quiz;

public record UserAnswerDTO(Long questionId, boolean randomSelection, String choosedOption, String correctOption) {

}
