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
    public ResponseEntity<AuthorDto> getPublisher(@PathVariable Long authorId,
                                                  @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.getPublisher(authorId, authorDto), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDto> createPublisher(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.createPublisher(authorDto), HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<AuthorDto> updatePublisher(@PathVariable Long authorId,
                                                     @RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorService.updatePublisher(authorId, authorDto), HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<String> deletePublisher(@PathVariable Long authorId,
                                                  @RequestBody AuthorDto authorDto) {
        authorService.deletePublisher(authorId, authorDto);
        return new ResponseEntity<>("Publisher deleted", HttpStatus.OK);
    }
}
