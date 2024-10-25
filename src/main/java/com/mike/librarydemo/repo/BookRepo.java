package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Long> {
}
