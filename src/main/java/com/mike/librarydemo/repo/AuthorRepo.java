package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepo extends JpaRepository<Author, Long> {

    Optional<Author> findByLastNameAndMiddleNameAndFirstName(String lastName, String middleName, String firstName);
}
