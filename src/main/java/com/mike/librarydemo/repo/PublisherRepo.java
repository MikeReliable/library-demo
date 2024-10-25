package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
}
