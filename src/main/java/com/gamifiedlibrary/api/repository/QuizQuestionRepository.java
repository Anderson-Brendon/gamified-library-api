package com.gamifiedlibrary.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.model.QuizQuestion;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, Long>{
	
	public List<QuizQuestion> findAllByBookId(Long id);
	
	/*public QuizQuestion create(QuizQuestion quizQuestion);
	
	public QuizQuestion update(int id, QuizQuestion quizQuestion);
	
	public QuizQuestion deleteById(int id);*/
	
}
