package com.mike.librarydemo.service;

import com.mike.librarydemo.dto.AuthorDto;

public interface AuthorService {

    AuthorDto getAuthor(Long authorId);

    AuthorDto createAuthor(AuthorDto authorDto);

    AuthorDto updateAuthor(Long authorId, AuthorDto authorDto);

    void deleteAuthor(Long authorId, AuthorDto authorDto);
}
