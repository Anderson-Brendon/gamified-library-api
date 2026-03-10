package com.gamifiedlibrary.api.infrastructure.dto.book;

public record ReadingListBookDTO(Long id, String title, String cover, int currentPage, boolean isComplete) {

}
