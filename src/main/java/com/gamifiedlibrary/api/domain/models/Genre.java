package com.gamifiedlibrary.api.domain.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Genre {
	
	public Genre() {
		
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int id;
	
	@NotBlank
	public String name;

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
