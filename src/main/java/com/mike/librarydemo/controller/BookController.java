package com.mike.librarydemo.controller;

import com.mike.librarydemo.dto.BookCreateDto;
import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.dto.BookDtoList;
import com.mike.librarydemo.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
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
    public ResponseEntity<BookCreateDto> createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.createBook(bookCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<BookCreateDto> updateBook(@PathVariable Long bookId,
                                                    @RequestBody @Valid BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.updateBook(bookId, bookCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }

    @GetMapping("/publishers/{publisherId}")
    public ResponseEntity<BookDtoList> getAllBooksByPublisher(@PathVariable Long publisherId,
                                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                              @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                                              @RequestParam(value = "year", defaultValue = "0", required = false) int year) {
        return new ResponseEntity<>(bookService.getAllBooksByPublisher(publisherId, pageNo, pageSize, year), HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}")
    public ResponseEntity<BookDtoList> getAllBooksByAuthor(@PathVariable Long authorId,
                                                           @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                                           @RequestParam(value = "year", required = false) int year) {
        return new ResponseEntity<>(bookService.getAllBooksByAuthor(authorId, pageNo, pageSize, year), HttpStatus.OK);
    }

}
