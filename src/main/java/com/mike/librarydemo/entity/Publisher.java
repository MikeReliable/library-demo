package com.mike.librarydemo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(country, publisher.country) && Objects.equals(city, publisher.city) && Objects.equals(email, publisher.email) && Objects.equals(telephone, publisher.telephone);
    }
}
