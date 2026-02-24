package com.gamifiedlibrary.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.model.AppUser;

//conexao com o banco de dados
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
	
	 Optional<AppUser> findByUsername(String username);//cria a query automaticamente
	 
	 boolean existsByUsername(String username);
	 
	 boolean existsByEmail(String username);
}
