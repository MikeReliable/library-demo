package com.mike.librarydemo.repo;

import com.mike.librarydemo.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepo extends JpaRepository<Publisher, Long> {
    Optional<Publisher> findByCountryAndAndCityAndAndEmailAndAndTelephone(String country, String city, String email, String telephone);
}
