package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.ReadingListBook;
import com.gamifiedlibrary.api.repository.ReadingListBookRepository;

@Service
public class ReadingListService {
	
	private ReadingListBookRepository readingListRepository;

	public ReadingListService(ReadingListBookRepository readingListRepository) {
		this.readingListRepository = readingListRepository;
	}
	
	public List<ReadingListBook> findReadingListByUserId(Long userId) {
		return readingListRepository.findByUserId(userId);
	}
}
