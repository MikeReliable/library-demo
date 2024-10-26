package com.mike.librarydemo.service;

import com.mike.librarydemo.dto.BookCreateDto;
import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.dto.BookDtoList;

public interface BookService {

    BookDto getBook(Long bookId);

    BookCreateDto createBook(BookCreateDto bookCreateDto);

    BookCreateDto updateBook(Long bookId, BookCreateDto bookCreateDto);

    void deleteBook(Long bookId);

    BookDtoList getAllBooksByPublisher(Long publisherId, int pageNo, int pageSize, int year);

    BookDtoList getAllBooksByAuthor(Long authorId, int pageNo, int pageSize, int year);
}
