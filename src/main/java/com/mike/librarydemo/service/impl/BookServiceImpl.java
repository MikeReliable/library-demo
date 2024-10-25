package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.service.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Override
    public BookDto getBook(Long bookId, BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        return null;
    }

    @Override
    public BookDto updateBook(Long bookId, BookDto bookDto) {
        return null;
    }

    @Override
    public void deleteBook(Long bookId, BookDto bookDto) {
    }
}
