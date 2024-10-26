package com.mike.librarydemo.dto;

import jakarta.validation.Valid;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.Set;

@Validated
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {

    @Valid BookDto bookDto;
    @Valid PublisherDto publisherDto;
    @Valid Set<AuthorDto> authorDtoSet;
}
