package com.mike.librarydemo.repo;

import com.mike.librarydemo.dto.AuthorDto;
import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.entity.Book;
import com.mike.librarydemo.entity.Publisher;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BookRepoTest {

    @Mock
    BookRepo bookRepo;
    @Mock
    private PublisherRepo publisherRepo;
    @Mock
    private AuthorRepo authorRepo;

    AutoCloseable autoCloseable;
    Book bookTest;
    Set<Book> bookSet;
    Author author;
    Set<Author> authorSet;
    Set<AuthorDto> authorDtoSet;
    Publisher publisher;
    Pageable pageable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

        pageable = PageRequest.of(0, 2);

        bookSet = new HashSet<>();
        authorSet = new HashSet<>();
        authorDtoSet = new HashSet<>();

        author = Author.builder()
                .firstName("firstNameFirst")
                .middleName("middleNameFirst")
                .lastName("lastNameFirst")
                .build();
        authorRepo.save(author);

        authorSet.add(author);

        publisher = Publisher.builder()
                .publisherId(1L)
                .country("countryFirst")
                .city("cityFirst")
                .email("email@email.first")
                .telephone("111-111")
                .build();
        publisherRepo.save(publisher);

        bookTest = Book.builder()
                .bookId(1L)
                .title("bookThirdTitle")
                .year(1900)
                .pages(300)
                .isbn("bookThirdIsbn")
                .publisher(publisher)
                .authors(authorSet)
                .build();
        bookRepo.save(bookTest);

        bookSet.add(bookTest);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void findByTitle_validResponse() {
        //given
        Optional<Book> bookOptional = Optional.of(bookTest);
        doReturn(bookOptional).when(this.bookRepo).findByTitle(bookTest.getTitle());
        //act
        assertThat(bookRepo.findByTitle("bookThirdTitle")
                //assert
                .isPresent())
                .isTrue();
        assertEquals(bookTest, bookRepo.findByTitle("bookThirdTitle").get());
    }

    @Test
    void findAllByPublisher_validResponse() {
        //given
        publisher = Publisher.builder()
                .publisherId(1L)
                .country("countryFirst")
                .city("cityFirst")
                .email("email@email.first")
                .telephone("111-111")
                .books(bookSet)
                .build();
        publisherRepo.save(publisher);

        Optional<Publisher> publisherOptional = Optional.of(publisher);

        doReturn(publisherOptional).when(this.publisherRepo).findById(publisher.getPublisherId());

        Page<Book> bookPage = new PageImpl(new ArrayList<>(publisher.getBooks()));
        doReturn(bookPage).when(this.bookRepo).findAllByPublisher(publisher, pageable);
        //assert
        assertThat(publisherRepo.findById(1L)).isPresent();
        assertEquals(bookRepo.findAllByPublisher(publisher, pageable).getContent().size(), publisher.getBooks().size());
    }

    @Test
    void findAllByAuthors_validResponse() {
        //given
        author.setBooks(bookSet);
        Page<Book> bookPage = new PageImpl(new ArrayList<>(author.getBooks()));
        doReturn(bookPage).when(this.bookRepo).findAllByAuthors(authorSet, pageable);
        //assert
        assertEquals(bookRepo.findAllByAuthors(authorSet, pageable).getContent().size(), author.getBooks().size());
    }
}