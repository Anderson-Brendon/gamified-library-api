package com.gamifiedlibrary.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gamifiedlibrary.api.domain.model.AppUser;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.AccountCreationDTO;
import com.gamifiedlibrary.api.infrastructure.utils.PasswordService;
import com.gamifiedlibrary.api.repository.AppUserRepository;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AppUserService {
	
	public AppUserService(AppUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	private AppUserRepository userRepository;
	
	public List<AppUser> findAllUsers() {
		return userRepository.findAll();
	}
	
	public AppUser findById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}

	public AppUser findByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
	
	public AppUser findByEmail(String email) {
		return userRepository.findByUsername(email).orElseThrow(() -> new EntityNotFoundException("User not found"));
	}
	
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}
	
	public boolean existByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
	public void updateUser(AppUser user) {
		userRepository.save(user);
	}

	public void createUser(AccountCreationDTO accountCreationDTO) {
		if(this.userRepository.existsByEmail(accountCreationDTO.email())){
			throw new EntityExistsException("Email already registered");
		}else if(this.userRepository.existsByUsername(accountCreationDTO.username())){
			throw new EntityExistsException("Username already registered");
		}
		String hashedPassword = PasswordService.createPasswordHash(accountCreationDTO.password());
		AppUser user = new AppUser(accountCreationDTO.username(), hashedPassword, accountCreationDTO.email());
		userRepository.save(user);
	}
}
