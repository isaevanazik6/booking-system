package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.PaymentRequest;
import com.example.bookingsystem.dto.PaymentCheckRequest;

public interface PaymentService {
    String checkPayment(PaymentCheckRequest paymentCheckRequest);

    String addPayment(PaymentRequest paymentRequest, long id);

    String setPayment(long id);

    String rollbackPayment(long id);
}
