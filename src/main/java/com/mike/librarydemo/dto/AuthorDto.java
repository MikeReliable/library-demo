package com.mike.librarydemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String lastName;
    private String middleName;
    private String firstName;
}
