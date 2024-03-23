package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.Order;
import com.example.bookingsystem.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addOrder(@RequestBody Order order) {
        Long id = orderService.addOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<String> confirmOrder(@PathVariable Long id) {
        String status = orderService.confirmOrder(id);
        return ResponseEntity.ok(status);
    }

    @PostMapping("/rollback/{id}")
    public ResponseEntity<String> rollbackOrder(@PathVariable Long id) {
        String status = orderService.rollbackPayment(id);
        return ResponseEntity.ok(status);
    }
}

