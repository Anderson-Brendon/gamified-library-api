package com.gamifiedlibrary.api.infrastructure.dto.book;

import com.gamifiedlibrary.api.domain.model.Author;
import com.gamifiedlibrary.api.domain.model.Genre;

public record BookDetailDTO(Long id, String title, String cover, String description, short releaseYear, String pdf, Author author, Genre genre, Float averageRating) {

}
