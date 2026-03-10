package com.gamifiedlibrary.api.infrastructure.book;

public record ReadingListBookDTO(Long id, String title, String cover, int currentPage, boolean isComplete) {

}
