package com.gamifiedlibrary.api.domain.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gamifiedlibrary.api.domain.service.FavoriteBookId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class FavoriteBook {
	
	public FavoriteBook() {
		
	}
	
    public FavoriteBook(AppUser user, Book book) {
		this.book = book;
		this.user = user;
		this.id = new FavoriteBookId(user.getId(), book.getId());
	}
	
	@EmbeddedId
	private FavoriteBookId id;
	
	@JsonIgnore
    @ManyToOne
    @MapsId("userId") // mapeia o valor do user para o id da entity embeddable
    @JoinColumn(name = "user_id")
	private AppUser user;
	
    @ManyToOne
    @MapsId("bookId") // mapeia o valor do book para o id da entity embeddable
    @JoinColumn(name = "book_id")
	private Book book;

	public FavoriteBookId getId() {
		return id;
	}

	public AppUser getUser() {
		return user;
	}

	public Book getBook() {
		return book;
	}
	
}
