package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.PaymentCheckRequest;
import com.example.bookingsystem.dto.PaymentRequest;
import com.example.bookingsystem.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServiceImpl paymentService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }


    @PostMapping("/check")
    public ResponseEntity<String> checkPayment(@RequestBody PaymentCheckRequest paymentRequest) {
        return ResponseEntity.status(201).body(paymentService.checkPayment(paymentRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> addPayment(@RequestBody PaymentRequest paymentRequest,
                                             @PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.addPayment(paymentRequest, id));
    }

    @PutMapping("/setStatus/{id}")
    public ResponseEntity<String> updateStatus(@PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.setPayment(id));
    }

    @PutMapping("/rollbackStatus/{id}")
    public ResponseEntity<String> rollbackStatus(@PathVariable int id) {
        return ResponseEntity.status(201).body(paymentService.rollbackPayment(id));
    }
}