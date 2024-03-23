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
public class Seat {
    @NotNull(message = "'row' field cannot be empty!")
    Integer row;
    @NotNull(message = "'place' field cannot be null!")
    Integer place;
    Long movieSessionId;
}
