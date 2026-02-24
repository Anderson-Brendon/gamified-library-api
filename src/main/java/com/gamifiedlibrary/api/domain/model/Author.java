package com.gamifiedlibrary.api.domain.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@NotBlank
	private String name;
	
	@OneToMany(mappedBy = "author",fetch = FetchType.LAZY)
	List<Book> books = new ArrayList<Book>();
	//mappedby indica o campo do outro objeto usado para o mapeamento, pra trazer os livros com id igual do autor
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
