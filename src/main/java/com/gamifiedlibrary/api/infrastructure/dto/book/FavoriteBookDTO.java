package com.gamifiedlibrary.api.infrastructure.book;

//para livros que foram adicionados na lista de leitura ou nos favoritos

public record FavoriteBookDTO(Long id, String title, String cover) {

}
