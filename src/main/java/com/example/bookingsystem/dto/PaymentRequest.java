package com.example.bookingsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PaymentRequest {

    private Integer price;

    private String accountCheck;

    private Long rentId;
}