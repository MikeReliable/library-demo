package com.mike.librarydemo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublisherDto {

    private String country;
    private String city;
    private String email;
    private String telephone;
}
