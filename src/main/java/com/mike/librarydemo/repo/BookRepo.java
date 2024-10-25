package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {

    Optional<Book> findByTitle(String title);
}
