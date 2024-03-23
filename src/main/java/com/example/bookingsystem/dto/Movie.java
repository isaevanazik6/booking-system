package com.example.bookingsystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Movie {
    @NotNull(message = "'name' field cannot be empty!")
    String name;
    @NotNull(message = "'yearOfCreation' field cannot be empty!")
    Integer yearOfCreation;
    @NotNull(message = "'director' field cannot be empty!")
    String director;
    @NotNull(message = "'duration' field cannot be empty!")
    String duration;
    @NotNull(message = "'country' field cannot be empty!")
    String country;
    @NotNull(message = "'description' field cannot be empty!")
    String description;
}
