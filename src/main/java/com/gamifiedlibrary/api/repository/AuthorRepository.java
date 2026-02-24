package com.gamifiedlibrary.api.repository;

import java.util.List;

import com.gamifiedlibrary.api.domain.model.Author;

public interface AuthorRepository {
	
	public List<Author> findAll();
	
	public Author findById(int id);
	
	public void create(Author author);
	
	public void updateById(int id, String authorName);
	
	public void deleteById(int id);
}
