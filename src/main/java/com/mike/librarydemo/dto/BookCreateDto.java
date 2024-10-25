package com.mike.librarydemo.dto;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {

    BookDto bookDto;
    PublisherDto publisherDto;
    Set<AuthorDto> authorDtoSet;
}
