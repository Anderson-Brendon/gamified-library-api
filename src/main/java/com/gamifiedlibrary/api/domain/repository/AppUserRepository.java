package com.gamifiedlibrary.api.domain.repository;

import java.util.List;
import com.gamifiedlibrary.api.domain.models.AppUser;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.AccountCreationDTO;
import com.gamifiedlibrary.api.infrastructure.dto.appuser.UserDTO;

//indica os metodos que o adapter deve implementar independente do orm utilizado
public interface AppUserRepository {
	
	public List<AppUser> findAll();
	
	public AppUser findById(Long id);
	
	public AppUser findByUsername(String username);
	
	public void create(AccountCreationDTO userDto);
	
	public void update(int userId, UserDTO userDto);
	
	public void deleteById(Long id);
}
