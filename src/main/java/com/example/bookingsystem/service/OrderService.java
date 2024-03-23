package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.Order;

public interface OrderService {
    Long addOrder(Order order);
    String confirmOrder(Long id, Long seatId);
    String rollbackPayment(Long id, Long seatId);
}
