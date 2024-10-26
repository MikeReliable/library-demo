package com.mike.librarydemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Book create/update input data")
public class BookCreateDto {

    @Valid BookDto bookDto;
    @Valid PublisherDto publisherDto;
    @Valid Set<AuthorDto> authorDtoSet;
}
