package com.mike.librarydemo.controller;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/{authorId}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long authorId                                                  ) {
        return new ResponseEntity<>(authorService.getAuthor(authorId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createAuthor(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDto> updateAuthor(@PathVariable Long authorId,
                                                     @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updateAuthor(authorId, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long authorId,
                                                  @RequestBody AuthorDto authorDto) {
        authorService.deleteAuthor(authorId, authorDto);
        return new ResponseEntity<>("Author deleted", HttpStatus.OK);
    }
}
