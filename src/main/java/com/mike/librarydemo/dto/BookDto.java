package com.mike.librarydemo.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    @NotBlank(message = "Field title cannot be empty")
    private String title;
    @Positive(message = "Enter the correct year value")
    @Digits(integer = 4, fraction = 0, message = "Enter the correct year value")
    private int year;
    @Positive(message = "Enter the correct pages value")
    @Digits(integer = 4, fraction = 0, message = "Enter the correct year value")
    private int pages;
    @NotBlank(message = "Field isbn cannot be empty")
    private String isbn;
}
