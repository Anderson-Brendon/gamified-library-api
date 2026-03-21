package com.gamifiedlibrary.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gamifiedlibrary.api.domain.model.ReadingListBook;
import com.gamifiedlibrary.api.domain.model.ReadingListBookId;


public interface ReadingListBookRepository extends JpaRepository<ReadingListBook, ReadingListBookId> {

	public List<ReadingListBook> findByUserId(Long id);
	
	public List<ReadingListBook> findByUserIdAndCompleteTrue(Long id);

	public List<ReadingListBook> findByUserIdAndCompleteFalse(Long id);
}
