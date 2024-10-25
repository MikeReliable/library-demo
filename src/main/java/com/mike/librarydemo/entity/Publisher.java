package com.mike.librarydemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    private String country;
    private String city;
    private String email;
    private String telephone;

    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

}
