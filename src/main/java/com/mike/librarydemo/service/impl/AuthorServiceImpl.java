package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.mapper.EntityMapper;
import com.mike.librarydemo.repo.AuthorRepo;
import com.mike.librarydemo.service.AuthorService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepo authorRepo;
    private EntityMapper mapper;

    @Override
    public AuthorDto getAuthor(Long authorId) {
        Author author = authorRepo.findById(authorId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Author with Id %s not found", authorId)));
        return mapper.toAuthorDto(author);
    }

    @Override
    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = mapper.toAuthor(authorDto);
        if (authorRepo.findByLastNameAndMiddleNameAndFirstName(authorDto.getLastName(), authorDto.getMiddleName(), authorDto.getFirstName()).isEmpty()) {
            authorRepo.save(author);
        } else throw new EntityExistsException("This author already exists");
        return authorDto;
    }

    @Override
    public AuthorDto updateAuthor(Long authorId, AuthorDto authorDto) {
        Author author;
        Author authorDb = authorRepo.findById(authorId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Author with Id %s not found", authorId)));
        if (authorRepo.findByLastNameAndMiddleNameAndFirstName(authorDto.getLastName(), authorDto.getMiddleName(), authorDto.getFirstName()).isPresent()) {
            author = authorRepo.findByLastNameAndMiddleNameAndFirstName(authorDto.getLastName(), authorDto.getMiddleName(), authorDto.getFirstName()).get();
            if (!authorId.equals(author.getAuthorId())) {
                throw new EntityExistsException("This author already exists");
            }
        }
        authorDb.setFirstName(authorDto.getFirstName());
        authorDb.setMiddleName(authorDto.getMiddleName());
        authorDb.setLastName(authorDto.getLastName());
        authorRepo.save(authorDb);
        return authorDto;
    }

    @Override
    public void deleteAuthor(Long authorId, AuthorDto authorDto) {
        Author author = authorRepo.findById(authorId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Author with Id %s not found", authorId)));
       if(author.getBooks().isEmpty()){
           authorRepo.delete(author);
       }else {
           throw new EntityExistsException("This author is still in use");
       }

    }
}
