package com.mike.librarydemo.controller;

import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.dto.BookCreateDto;
import com.mike.librarydemo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookCreateDto> createBook(@RequestBody BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.createBook(bookCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookCreateDto> updateBook(@PathVariable Long bookId,
                                              @RequestBody BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.updateBook(bookId, bookCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId,
                                             @RequestBody BookDto bookDto) {
        bookService.deleteBook(bookId, bookDto);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }
}
