package com.gamifiedlibrary.api.domain.repository;

import java.util.List;

import com.gamifiedlibrary.api.domain.models.AppUser;

public interface AppUserRepository {
	
	public List<AppUser> findAll();
	
	public AppUser findById(int id);
	
	public AppUser findByUsername(String username);
	
	public void create(UserDTO userDto);
	
	public void update(int userId, UserDTO userDto);
	
	public void deleteById(int id);
}
