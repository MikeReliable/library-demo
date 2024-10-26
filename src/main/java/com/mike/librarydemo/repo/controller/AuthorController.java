package com.mike.librarydemo.repo.controller;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
@Tag(name = "Author", description = "Author CRUD methods")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{authorId}")
    @Operation(summary = "Get author by Id")
    @Cacheable(value = "author", key = "#authorId")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId) {
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create author")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    @Operation(summary = "Update author by Id")
    @CachePut(value = "author", key = "#authorId")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long authorId,
                                                  @RequestBody @Valid AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(authorId, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    @Operation(summary = "Delete author by Id")
    @CacheEvict(value = "author", key = "#authorId")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId) {
        authorService.deleteAuthor(authorId);
        return new ResponseEntity<>("Author deleted", HttpStatus.OK);
    }
}
