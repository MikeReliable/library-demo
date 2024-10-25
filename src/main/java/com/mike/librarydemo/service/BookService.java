package com.mike.librarydemo.service;

import com.mike.librarydemo.dto.BookDto;

public interface BookService {

    BookDto getBook(Long bookId);

    BookDto createBook(BookDto bookDto);

    BookDto updateBook(Long bookId, BookDto bookDto);

    void deleteBook(Long bookId, BookDto bookDto);
}
