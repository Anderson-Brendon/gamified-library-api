package com.gamifiedlibrary.api.domain.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class AppUser {
	
	public AppUser() {
		
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotBlank
	@Size(min = 6, max = 15, message = "Name must be between 6 and 15 characters long.")
	private String username;
	
	private String profilePic;
	
	@NotBlank
	@Email()
	private String email;
	
	//aqui vou validar o tamanho da senha por fora
	@NotBlank
	private String passwordHash;
	
	private boolean isVerified;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)//indica que só vai buscar os dados ao acessar o campo
	private List<FavoriteBook> favoritesBooks = new ArrayList<FavoriteBook>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	List<QuizResult> quizResults = new ArrayList<QuizResult>();;

	@Column(length = 6, nullable = false)
	private String verificationCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public List<FavoriteBook> getFavoritesBooks() {
		return favoritesBooks;
	}

	public void setFavoritesBooks(List<FavoriteBook> favoritesBooks) {
		this.favoritesBooks = favoritesBooks;
	}
	
	
}
