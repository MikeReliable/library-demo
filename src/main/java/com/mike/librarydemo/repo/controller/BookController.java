package com.mike.librarydemo.repo.controller;

import com.mike.librarydemo.dto.BookCreateDto;
import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.dto.BookDtoList;
import com.mike.librarydemo.service.BookService;
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
@RequestMapping("/books")
@Tag(name = "Book", description = "Book CRUD methods")
public class BookController {

    private final BookService bookService;

    @GetMapping("/{bookId}")
    @Operation(summary = "Get book by Id")
    @Cacheable(value = "book", key = "#bookId")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(bookService.getBook(bookId), HttpStatus.OK);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create book")
    public ResponseEntity<BookCreateDto> createBook(@RequestBody @Valid BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.createBook(bookCreateDto), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    @Operation(summary = "Update book by Id")
    @CachePut(value = "book", key = "#bookId")
    public ResponseEntity<BookCreateDto> updateBook(@PathVariable Long bookId,
                                                    @RequestBody @Valid BookCreateDto bookCreateDto) {
        return new ResponseEntity<>(bookService.updateBook(bookId, bookCreateDto), HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    @Operation(summary = "Delete book by Id")
    @CacheEvict(value = "book", key = "#bookId")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted", HttpStatus.OK);
    }

    @GetMapping("/publishers/{publisherId}")
    @Operation(summary = "Get all books by publisher")
    public ResponseEntity<BookDtoList> getAllBooksByPublisher(@PathVariable Long publisherId,
                                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                              @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                                              @RequestParam(value = "year", defaultValue = "0", required = false) int year) {
        return new ResponseEntity<>(bookService.getAllBooksByPublisher(publisherId, pageNo, pageSize, year), HttpStatus.OK);
    }

    @GetMapping("/authors/{authorId}")
    @Operation(summary = "Get all books by author")
    public ResponseEntity<BookDtoList> getAllBooksByAuthor(@PathVariable Long authorId,
                                                           @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                                           @RequestParam(value = "pageSize", defaultValue = "2", required = false) int pageSize,
                                                           @RequestParam(value = "year", required = false) int year) {
        return new ResponseEntity<>(bookService.getAllBooksByAuthor(authorId, pageNo, pageSize, year), HttpStatus.OK);
    }

}
