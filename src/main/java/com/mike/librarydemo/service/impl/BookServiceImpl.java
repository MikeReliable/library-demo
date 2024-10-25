package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.dto.BookCreateDto;
import com.mike.librarydemo.dto.BookDto;
import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.entity.Book;
import com.mike.librarydemo.entity.Publisher;
import com.mike.librarydemo.mapper.EntityMapper;
import com.mike.librarydemo.repo.AuthorRepo;
import com.mike.librarydemo.repo.BookRepo;
import com.mike.librarydemo.repo.PublisherRepo;
import com.mike.librarydemo.service.BookService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private BookRepo bookRepo;
    private EntityMapper mapper;
    private PublisherRepo publisherRepo;
    private AuthorRepo authorRepo;

    @Override
    public BookDto getBook(Long bookId) {
        Book book = bookRepo.findById(bookId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Book with Id %s not found", bookId)));
        return mapper.toBookDto(book);
    }

    @Override
    public BookCreateDto createBook(BookCreateDto bookCreateDto) {
        Book book = mapper.toBook(bookCreateDto.getBookDto());
        if (bookRepo.findByTitle(bookCreateDto.getBookDto().getTitle()).isEmpty()) {
            setPublisher(bookCreateDto, book);
            setAuthors(bookCreateDto, book);
            bookRepo.save(book);
        } else throw new EntityExistsException("This book already exists");
        return bookCreateDto;
    }

    @Override
    public BookCreateDto updateBook(Long bookId, BookCreateDto bookCreateDto) {
        Book book = bookRepo.findById(bookId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Book with Id %s not found", bookId)));
        if (bookCreateDto.getBookDto() != null) {
            if (bookRepo.findByTitle(bookCreateDto.getBookDto().getTitle()).isPresent() &&
                    !bookRepo.findByTitle(bookCreateDto.getBookDto().getTitle()).get().equals(book)) {
                throw new EntityExistsException("Book with this title already exists");
            }
            book.setTitle(bookCreateDto.getBookDto().getTitle());
            book.setYear(bookCreateDto.getBookDto().getYear());
            book.setPages(bookCreateDto.getBookDto().getPages());
            book.setIsbn(bookCreateDto.getBookDto().getIsbn());
        } else {
            bookCreateDto.setBookDto(mapper.toBookDto(book));
        }
        if (bookCreateDto.getPublisherDto() != null) {
            setPublisher(bookCreateDto, book);
        } else {
            bookCreateDto.setPublisherDto(mapper.toPublisherDto(book.getPublisher()));
        }
        if (bookCreateDto.getAuthorDtoSet() != null) {
            setAuthors(bookCreateDto, book);
        } else {
            Set<AuthorDto> authorDtoSet = new HashSet<>();
            book.getAuthors().forEach(author -> {
                AuthorDto authorDto = new AuthorDto();
                authorDto.setLastName(author.getLastName());
                authorDto.setMiddleName(author.getMiddleName());
                authorDto.setFirstName(author.getFirstName());
                authorDtoSet.add(authorDto);
            });
            bookCreateDto.setAuthorDtoSet(authorDtoSet);
        }
        bookRepo.save(book);

        return bookCreateDto;
    }

    private void setAuthors(BookCreateDto bookCreateDto, Book book) {
        Set<Author> authors = new HashSet<>();
        bookCreateDto.getAuthorDtoSet().forEach(authorDto -> {
            if (authorRepo.findByLastNameAndMiddleNameAndFirstName(authorDto.getLastName(), authorDto.getMiddleName(),
                    authorDto.getFirstName()).isPresent()) {
                authors.add(authorRepo.findByLastNameAndMiddleNameAndFirstName(authorDto.getLastName(), authorDto.getMiddleName(),
                        authorDto.getFirstName()).get());
            } else {
                Author author = mapper.toAuthor(authorDto);
                Author authorDb = authorRepo.save(author);
                authors.add(authorDb);
            }
        });
        book.setAuthors(authors);
    }

    private void setPublisher(BookCreateDto bookCreateDto, Book book) {
        Publisher publisher;
        if (publisherRepo.findByCountryAndAndCityAndAndEmailAndAndTelephone(bookCreateDto.getPublisherDto().getCountry(), bookCreateDto.getPublisherDto().getCity(), bookCreateDto.getPublisherDto().getEmail(), bookCreateDto.getPublisherDto().getTelephone()).isEmpty()) {
            Publisher newPublisher = mapper.toPublisher(bookCreateDto.getPublisherDto());
            publisher = publisherRepo.save(newPublisher);
        } else {
            publisher = publisherRepo.findByCountryAndAndCityAndAndEmailAndAndTelephone(bookCreateDto.getPublisherDto().getCountry(), bookCreateDto.getPublisherDto().getCity(), bookCreateDto.getPublisherDto().getEmail(), bookCreateDto.getPublisherDto().getTelephone()).get();
        }
        book.setPublisher(publisher);
    }

    @Override
    public void deleteBook(Long bookId, BookDto bookDto) {
        Book book = bookRepo.findById(bookId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Book with Id %s not found", bookId)));
        book.setPublisher(null);
        book.setAuthors(null);
        bookRepo.delete(book);
    }
}
