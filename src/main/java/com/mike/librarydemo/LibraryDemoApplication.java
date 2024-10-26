package com.mike.librarydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LibraryDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryDemoApplication.class, args);
    }

}
