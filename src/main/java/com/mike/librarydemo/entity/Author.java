package com.mike.librarydemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    private String lastName;
    private String middleName;
    private String firstName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    private Set<Book> books;
}
