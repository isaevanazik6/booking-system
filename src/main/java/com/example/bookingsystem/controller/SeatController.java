package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.Seat;
import com.example.bookingsystem.service.SeatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addSeat(@RequestBody Seat seat) {
        Long id = seatService.add(seat);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        Optional<Seat> seat = seatService.getById(id);
        return seat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/available")
    public ResponseEntity<List<Seat>> getAllAvailableSeats() {
        List<Seat> availableSeats = seatService.getAllByAvailableStatus();
        return ResponseEntity.ok(availableSeats);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateSeat(@RequestBody Seat updatedSeat, @PathVariable Long id) {
        Long updatedId = seatService.updateSeat(updatedSeat, id);
        return ResponseEntity.ok(updatedId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.noContent().build();
    }
}

