package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Author;
import com.mike.librarydemo.entity.Book;
import com.mike.librarydemo.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface BookRepo extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);

    Page<Book> findAllByPublisher(Publisher publisher, Pageable pageable);

    Page<Book> findAllByAuthors(Set<Author> authors, Pageable pageable);
}
