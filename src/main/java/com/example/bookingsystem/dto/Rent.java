package com.example.bookingsystem.dto;

import com.example.bookingsystem.entity.StatusRent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Rent {
    Long id;

    String row;

    String place;

    Boolean isRent;

    StatusRent statusRent;

    Long movieId;
}
