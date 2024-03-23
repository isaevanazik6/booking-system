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
public class Cinema {
    @NotNull(message = "'name' field cannot be empty!")
    String name;
    @NotNull(message = "'location' field cannot be empty!")
    String location;
    @NotNull(message = "'capacity' field cannot be empty!")
    String capacity;
}
