package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Override
    public AuthorDto getPublisher(Long authorId, AuthorDto authorDto) {
        return null;
    }

    @Override
    public AuthorDto createPublisher(AuthorDto authorDto) {
        return null;
    }

    @Override
    public AuthorDto updatePublisher(Long authorId, AuthorDto authorDto) {
        return null;
    }

    @Override
    public void deletePublisher(Long authorId, AuthorDto authorDto) {
    }
}
