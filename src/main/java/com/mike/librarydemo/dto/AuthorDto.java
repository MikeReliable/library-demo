package com.mike.librarydemo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    @NotBlank(message = "Field lastName cannot be empty")
    @Size(min = 3, max = 20, message = "LastName length must be from 3 to 20 characters")
    private String lastName;
    private String middleName;
    @NotBlank(message = "Field firstName cannot be empty")
    @Size(min = 3, max = 20, message = "FirstName length must be from 3 to 20 characters")
    private String firstName;
}
