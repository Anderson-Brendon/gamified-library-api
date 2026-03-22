package com.gamifiedlibrary.api.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.domain.model.ReadingListBook;
import com.gamifiedlibrary.api.infrastructure.dto.book.ReadingListBookDTO;
import com.gamifiedlibrary.api.infrastructure.dto.book.ReadingListUpdateDTO;
import com.gamifiedlibrary.api.infrastructure.utils.CustomAPIMessage;
import com.gamifiedlibrary.api.repository.ReadingListBookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ReadingListService {
	
	private ReadingListBookRepository readingListRepository;
	
	private AppUserService appUserServices;

	public ReadingListService(ReadingListBookRepository readingListRepository, AppUserService appUserServices) {
		this.readingListRepository = readingListRepository;
		this.appUserServices = appUserServices;
	}
	
	public List<ReadingListBookDTO> findReadingListByUserId(Long userId) {
		List<ReadingListBookDTO> readingList = readingListRepository.findByUserId(userId).stream().map(userList -> 
		new ReadingListBookDTO(
				userList.getBook().getId(),
				userList.getBook().getTitle(), 
				userList.getBook().getCover(),
				userList.getBook().getSlug(),
				userList.getCurrentPage(),
				userList.getIsComplete())).toList();
				
		return readingList;
	}

	public List<ReadingListBookDTO> findBooksFromReadingListCompleted(Long userId) {
		List<ReadingListBookDTO> readingList = readingListRepository.findByUserIdAndCompleteTrue(userId).stream().map(userList -> 
		new ReadingListBookDTO(
				userList.getBook().getId(),
				userList.getBook().getTitle(), 
				userList.getBook().getCover(),
				userList.getBook().getSlug(),
				userList.getCurrentPage(),
				userList.getIsComplete())).toList();
				
		return readingList;
	}

	public List<ReadingListBookDTO> findBooksFromReadingListUnfinished(Long userId) {
		List<ReadingListBookDTO> readingList = readingListRepository.findByUserIdAndCompleteFalse(userId).stream().map(userList -> 
		new ReadingListBookDTO(
				userList.getBook().getId(),
				userList.getBook().getTitle(), 
				userList.getBook().getCover(),
				userList.getBook().getSlug(),
				userList.getCurrentPage(),
				userList.getIsComplete())).toList();
				
		return readingList;
	}
	
	public void updateBookFromReadingList(Long userId, Long bookId, ReadingListUpdateDTO readingListUpdateDTO) {
		
		
        AppUser user = this.appUserServices.findById(userId);
		
		Optional<ReadingListBook> optionalBookFromList = user.getBooksOnList().stream().filter(b -> Objects.equals(b.getBook().getId(), bookId)).findFirst();
		
		if(optionalBookFromList.isPresent()) {
			
			ReadingListBook book = optionalBookFromList.get();
			
			if (readingListUpdateDTO.currentPage() != null) {
			    book.setCurrentPage(readingListUpdateDTO.currentPage());
			}

			if (readingListUpdateDTO.completed() != null) {
			    book.setComplete(readingListUpdateDTO.completed());
			}
			
			appUserServices.updateUser(user);
			
		}else {
			throw new EntityNotFoundException(("Book not found in reading list"));
		}
	}
	
}

/*
 Optional.ofNullable(readingListUpdateDTO.currentPage()).ifPresent(value -> book.setCurrentPage(value));
			
			Optional.ofNullable(readingListUpdateDTO.completed()).ifPresent(book::setComplete);*/
