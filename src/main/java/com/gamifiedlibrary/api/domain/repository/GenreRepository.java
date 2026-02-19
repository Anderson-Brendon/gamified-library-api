package com.gamifiedlibrary.api.domain.repository;

import java.util.List;

import com.gamifiedlibrary.api.domain.models.Genre;

public interface GenreRepository {
	
	public List<Genre> findAll();
	
	public Genre findById(int id);
	
	public void update(Genre genre);
	
	public void deleteById(int id);
	
}
