package com.gamifiedlibrary.api.domain.repository;

import java.util.List;

import com.gamifiedlibrary.api.domain.models.Author;

public interface AuthorRepository {
	
	public List<Author> findAll();
	
	public Author findById(int id);
	
	public void create(Author author);
	
	public void updateById(int id, String authorName);
	
	public void deleteById(int id);
}
