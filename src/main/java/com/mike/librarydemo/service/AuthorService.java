package com.mike.librarydemo.service;

import com.mike.librarydemo.dto.AuthorDto;

public interface AuthorService {

    AuthorDto getPublisher(Long authorId, AuthorDto authorDto);

    AuthorDto createPublisher(AuthorDto authorDto);

    AuthorDto updatePublisher(Long authorId, AuthorDto authorDto);

    void deletePublisher(Long authorId, AuthorDto authorDto);
}
