package com.gamifiedlibrary.api.adapter.output.db.AppUser;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gamifiedlibrary.api.domain.models.AppUser;
import com.gamifiedlibrary.api.domain.repository.AppUserRepository;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.AccountCreationDTO;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.UserDTO;

import jakarta.persistence.EntityNotFoundException;
//adapter que implementa os metodos do appuser repository e utiliza o AppUserJpa pra manipular dados

@Service
public class AppUserRepositoryAdapter implements AppUserRepository{
	
	//dependencia inserida automaticamente
	public AppUserRepositoryAdapter(AppUserJPA userRepository) {
		this.userRepository = userRepository;
	}
	
	private final AppUserJPA userRepository;

	@Override
	public List<AppUser> findAll() {
		return userRepository.findAll();
	}

	@Override
	public AppUser findById(Long id) {
		// TODO Auto-generated method stub
		AppUser user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		return user;
	}

	@Override
	public AppUser findByUsername(String username) {
		// TODO Auto-generated method stub
		AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new EntityNotFoundException("User not found"));
		return user;
	}

	//update e create usam o mesmo metodo "save" mas por motivos de organização vou separar
	@Override
	public void create(AccountCreationDTO accountCreationDTO) {
		AppUser user = new AppUser();
		user.setUsername(accountCreationDTO.username());
		user.setEmail(accountCreationDTO.email());
		user.setPasswordHash(accountCreationDTO.password());//adicionar hash 
		userRepository.save(user);
	}

	@Override
	public void update(int userId, UserDTO userDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		AppUser user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
		userRepository.delete(user);	
	}

}
