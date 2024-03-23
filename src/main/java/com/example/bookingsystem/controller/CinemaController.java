package com.example.bookingsystem.controller;
import com.example.bookingsystem.dto.Cinema;
import com.example.bookingsystem.entity.CinemaEntity;
import com.example.bookingsystem.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cinemas")
@RequiredArgsConstructor
public class CinemaController {

    private final CinemaService cinemaService;

    @PostMapping
    public ResponseEntity<Long> createCinema(@RequestBody Cinema cinema) {
        Long cinemaId = cinemaService.createCinema(cinema);
        return new ResponseEntity<>(cinemaId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CinemaEntity>> getAllCinemas() {
        List<CinemaEntity> cinemas = cinemaService.getAllCinemas();
        return ResponseEntity.ok(cinemas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> getCinemaById(@PathVariable Long id) {
        Optional<Cinema> cinemaOptional = cinemaService.getCinemaById(id);
        return cinemaOptional.map(cinema -> ResponseEntity.ok().body(cinema))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateCinema(@PathVariable Long id, @RequestBody Cinema cinema) {
        Long updatedCinemaId = cinemaService.updateCinema(cinema, id);
        return ResponseEntity.ok(updatedCinemaId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable Long id) {
        cinemaService.deleteCinema(id);
        return ResponseEntity.noContent().build();
    }
}

