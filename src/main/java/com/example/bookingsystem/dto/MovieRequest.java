package com.example.bookingsystem.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieRequest {
    Long id;
    String name;
    Integer yearOfCreation;
    String director;
    String duration;
    String country;
    String description;
}
