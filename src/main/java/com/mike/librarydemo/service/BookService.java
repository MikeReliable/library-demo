package com.mike.librarydemo.service;

import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.dto.BookCreateDto;

public interface BookService {

    BookDto getBook(Long bookId);

    BookCreateDto createBook(BookCreateDto bookCreateDto);

    BookCreateDto updateBook(Long bookId, BookCreateDto bookCreateDto);

    void deleteBook(Long bookId, BookDto bookDto);
}
