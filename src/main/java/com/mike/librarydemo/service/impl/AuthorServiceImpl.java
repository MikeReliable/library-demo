package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.repo.AuthorRepo;
import com.mike.librarydemo.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepo authorRepo;

    @Override
    public AuthorDto getAuthor(Long authorId) {
        Author author = authorRepo.findById(authorId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Author with Id %s not found", authorId)));
        return null;
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        return null;
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
        return null;
    }

    @Override
    public void deleteAuthor(Long authorId, AuthorDto authorDto) {
    }
}
