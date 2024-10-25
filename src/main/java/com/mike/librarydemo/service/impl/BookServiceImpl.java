package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.entity.Book;
import com.mike.librarydemo.repo.BookRepo;
import com.mike.librarydemo.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;

    @Override
    public BookDto getBook(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Book with Id %s not found", bookId)));
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
