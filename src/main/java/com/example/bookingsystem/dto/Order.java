package com.example.bookingsystem.dto;

import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Min(value = 1, message = "numberOfTickets cannot be less than 1!")
    Integer numberOfTickets;
    @Min(value = 100, message = "totalPrice cannot be less than 100!")
    Double totalPrice;
    Long sessionId;
    Long seatId;
}
