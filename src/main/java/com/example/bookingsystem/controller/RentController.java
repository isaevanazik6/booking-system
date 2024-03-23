package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.RentRequest;
import com.example.bookingsystem.service.RentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @GetMapping("/{id}")
    public ResponseEntity<RentRequest> getRentById(@PathVariable Long id) {
        Optional<RentRequest> rentOptional = rentService.getById(id);
        return rentOptional.map(rent -> ResponseEntity.ok().body(rent))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/rent-place")
    public ResponseEntity<String> rentPlace(@PathVariable Long id) {
        String result = rentService.rentPlace(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{id}/rollback")
    public ResponseEntity<String> rollbackRent(@PathVariable Long id) {
        String result = rentService.rollbackRent(id);
        return ResponseEntity.ok(result);
    }
}

