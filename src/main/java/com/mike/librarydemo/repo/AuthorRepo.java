package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author, Long> {
}
