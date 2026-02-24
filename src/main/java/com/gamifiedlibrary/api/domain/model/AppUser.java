package com.gamifiedlibrary.api.domain.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
	
    public AppUser(String username, String password, String email) {
		this.username = username;
		this.passwordHash = password;
		this.email = email;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(min = 6, max = 15, message = "Name must be between 6 and 16 characters long.")
	@Column(unique=true)
	private String username;
	
	private String profilePic;
	
	@NotBlank
	@Email()
	@Column(unique=true)
	private String email;
	
	
	@NotBlank
	private String passwordHash;

	private boolean admin;
	
	private boolean isVerified;
	@JsonIgnore // não serializar em json ao solicitar usuario
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	List<QuizResult> quizResults = new ArrayList<QuizResult>();
	
	                             //indica que só vai buscar os dados ao acessar o campo
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<FavoriteBook> favoritesBooks = new HashSet<>();	
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<ReadingListBook> booksOnList = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Review> bookReviews = new HashSet<>();

	@Column(nullable = true)
	private String verificationCode;
	
	public void addBookAsFavorite(Book book) {//aggregate root
		this.favoritesBooks.add(new FavoriteBook(this, book));
	}
	
    public void removeBookFromFavorite(Book book) {
		this.favoritesBooks.removeIf(f -> f.getBook().equals(book));
	}
    
    public void addBookOnList(Book book) {
		this.booksOnList.add(new ReadingListBook(this, book, 0, false));
	}
	
    public void removeBookFromList(Book book) {
		this.booksOnList.removeIf(bookOnList -> bookOnList.getBook().equals(book));
	}
    
    public void addReview(Book book, int rate, String comment) {//aggregate root
		this.bookReviews.add(new Review(this, book, rate, comment));
	}
    
    public void editReview(int bookId, int rate, String comment) {
		Review foundReview = this.bookReviews.stream().filter(b -> b.getBook().getId() == (bookId)).
				findFirst().orElseThrow(() -> new IllegalArgumentException("Review not found"));
		foundReview.update(rate, comment);
	}
    
    public void removeReview(Book book) {
		this.bookReviews.removeIf(r -> r.getBook().equals(book));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public Set<FavoriteBook> getFavoritesBooks() {
		return favoritesBooks;
	}

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
	
	
}
