package com.mike.librarydemo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Publisher input/output data")
public class PublisherDto {

    @NotBlank(message = "Field country cannot be empty")
    @Size(min = 3, max = 100, message = "The length of the country name must be between 3 and 100 characters")
    private String country;
    @NotBlank(message = "Field city cannot be empty")
    @Size(min = 3, max = 100, message = "The length of the city name must be between 3 and 100 characters")
    private String city;
    @NotBlank(message = "Field email cannot be empty")
    @Email(message = "Email address has invalid format")
    private String email;
    @NotBlank(message = "Field telephone cannot be empty")
    private String telephone;
}
