package com.gamifiedlibrary.api.adapter.output.db.AppUser;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gamifiedlibrary.api.domain.models.AppUser;

//conexao com o banco de dados
@Repository
public interface AppUserJPA extends JpaRepository<AppUser, Long>{
	
	 Optional<AppUser> findByUsername(String username);//cria a query automaticamente
}
