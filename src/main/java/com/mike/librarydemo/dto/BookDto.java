package com.mike.librarydemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String title;
    private int year;
    private int pages;
    private String isbn;
}
