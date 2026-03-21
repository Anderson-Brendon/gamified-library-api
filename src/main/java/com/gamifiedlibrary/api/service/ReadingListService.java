package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.infrastructure.dto.book.ReadingListBookDTO;
import com.gamifiedlibrary.api.repository.ReadingListBookRepository;

@Service
public class ReadingListService {
	
	private ReadingListBookRepository readingListRepository;

	public ReadingListService(ReadingListBookRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}
	
	public List<ReadingListBookDTO> findReadingListByUserId(Long userId) {
		List<ReadingListBookDTO> readingList = readingListRepository.findByUserId(userId).stream().map(userList -> 
		new ReadingListBookDTO(
				userList.getBook().getId(),
				userList.getBook().getTitle(), 
				userList.getBook().getCover(), 
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
				userList.getCurrentPage(),
				userList.getIsComplete())).toList();
				
		return readingList;
	}
}
