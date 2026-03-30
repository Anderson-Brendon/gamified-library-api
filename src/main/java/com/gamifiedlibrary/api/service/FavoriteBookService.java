package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.domain.model.Book;
import com.gamifiedlibrary.api.infrastructure.dto.book.FavoriteBookDTO;
import com.gamifiedlibrary.api.repository.FavoriteBookRepository;

@Service
public class FavoriteBookService {

    private final AppUserService appUserService;

    private FavoriteBookRepository favoriteBookRepository;
    
    private BookService bookService;

    public FavoriteBookService(FavoriteBookRepository favoriteBookRepository, AppUserService appUserService, BookService bookService) {
        this.favoriteBookRepository = favoriteBookRepository;
        this.appUserService = appUserService;
        this.bookService = bookService;
    }

    public List<FavoriteBookDTO> FindFavoritesBooksByUserId(Long userId){
        List<FavoriteBookDTO> favorites = this.favoriteBookRepository.findByUserId(userId).stream().map
        (userBook -> new FavoriteBookDTO(
            userBook.getBook().getId(),
            userBook.getBook().getTitle(),
            userBook.getBook().getCover(),
        	userBook.getBook().getSlug())).toList();
            return favorites;
    }
    
    public boolean isBookOnUserFavorites(Long bookId, Long userId) {
    	return favoriteBookRepository.existsByBookIdAndUserId(bookId, userId);
    }
    
    public void addBookToUserFavorites(Long userId, Long bookId) {
    	AppUser user = appUserService.findById(userId);
    	Book book = bookService.findEntityById(bookId);
    	user.addBookAsFavorite(book);
    	appUserService.updateUser(user);
    }

}

/*public Book removeFavoriteBook(Long userId, Long BookId) {
return this.favoriteBookRepository.findByUserId(userId);
}*/
