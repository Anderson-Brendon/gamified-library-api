package com.gamifiedlibrary.api.application.usecase;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.adapter.output.db.AppUser.AppUserRepositoryAdapter;
import com.gamifiedlibrary.api.domain.models.AppUser;
import com.gamifiedlibrary.api.domain.repository.AppUserRepository;

//regras devem ficar aqui

@Service
public class AppUserService {
	
	
	AppUserRepository userRepository;//spring vai inserir o adapter que implementa essa interface automaticamente
	
	public AppUserService(AppUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<AppUser> findAllUsers() {
		return userRepository.findAll();
	}
	
	public AppUser findById(Long id) {
		return userRepository.findById(id);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
}
