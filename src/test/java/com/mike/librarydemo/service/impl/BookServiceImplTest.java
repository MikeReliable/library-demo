package com.mike.librarydemo.service.impl;

import com.mike.librarydemo.dto.BookCreateDto;
import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.entity.Book;
import com.mike.librarydemo.entity.Publisher;
import com.mike.librarydemo.mapper.EntityMapper;
import com.mike.librarydemo.mapper.EntityMapperImpl;
import com.mike.librarydemo.repo.AuthorRepo;
import com.mike.librarydemo.repo.BookRepo;
import com.mike.librarydemo.repo.PublisherRepo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepo bookRepo;
    @Mock
    private PublisherRepo publisherRepo;
    @Mock
    private AuthorRepo authorRepo;
    @Mock
    EntityMapper entityMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    AutoCloseable autoCloseable;
    Book bookTest;
    Set<Book> bookSet;
    Author author;
    Set<Author> authorSet;
    Publisher publisher;
    BookCreateDto bookCreateDto;

    @BeforeEach
    void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        authorSet = new HashSet<>();
        bookSet = new HashSet<>();
        entityMapper = new EntityMapperImpl();
        bookCreateDto = new BookCreateDto();

        author = Author.builder()
                .firstName("firstNameFirst")
                .middleName("middleNameFirst")
                .lastName("lastNameFirst")
                .build();

        authorSet.add(author);

        publisher = Publisher.builder()
                .publisherId(1L)
                .country("countryFirst")
                .city("cityFirst")
                .email("email@email.first")
                .telephone("111-111")
                .build();

        bookTest = Book.builder()
                .bookId(1L)
                .title("bookThirdTitle")
                .year(1900)
                .pages(300)
                .isbn("bookThirdIsbn")
                .publisher(publisher)
                .authors(authorSet)
                .build();

        bookSet.add(bookTest);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void updateBook_validResponse() {
        //given
        ArgumentCaptor<Book> entityArgumentCaptor = ArgumentCaptor.forClass(Book.class);
        bookTest = Book.builder()
                .bookId(1L)
                .title("bookChangedTitle") //updated value
                .year(1900)
                .pages(300)
                .isbn("bookThirdIsbn")
                .publisher(publisher)
                .authors(authorSet) //updated value
                .build();
        bookCreateDto.setBookDto(entityMapper.toBookDto(bookTest));

        Optional<Book> bookOptional = Optional.of(bookTest);
        doReturn(bookOptional).when(this.bookRepo).findById(bookTest.getBookId());
        //act
        BookCreateDto responseDto = bookService.updateBook(1L, bookCreateDto);
        verify(bookRepo, times(1)).save(entityArgumentCaptor.capture());
        //assert
        assertEquals(responseDto.getBookDto().getTitle(), bookTest.getTitle());
        assertEquals(responseDto.getAuthorDtoSet().size(), bookTest.getAuthors().size());
        assertEquals(responseDto.getBookDto().getYear(), bookTest.getYear());
    }

    @Test
    void deleteBook_validResponse() {
        //given
        Optional<Book> bookOptional = Optional.of(bookTest);
        doReturn(bookOptional).when(this.bookRepo).findById(bookTest.getBookId());
        //act
        bookService.deleteBook(bookTest.getBookId());
        //assert
        verify(bookRepo).delete(bookTest);
    }
}