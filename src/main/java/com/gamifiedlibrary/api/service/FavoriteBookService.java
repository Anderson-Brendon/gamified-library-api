package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.infrastructure.dto.book.FavoriteBookDTO;
import com.gamifiedlibrary.api.repository.FavoriteBookRepository;

@Service
public class FavoriteBookService {

    private FavoriteBookRepository favoriteBookRepository;

    public FavoriteBookService(FavoriteBookRepository favoriteBookRepository) {
        this.favoriteBookRepository = favoriteBookRepository;
    }

    public List<FavoriteBookDTO> FindFavoritesBooksByUserId(Long userId){
        List<FavoriteBookDTO> favorites = this.favoriteBookRepository.findByUserId(userId).stream().map
        (userBook -> new FavoriteBookDTO(
            userBook.getBook().getId(),
            userBook.getBook().getTitle(),
            userBook.getBook().getCover())).toList();
            return favorites;
    }

}
