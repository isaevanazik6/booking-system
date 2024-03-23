package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.MovieSession;
import com.example.bookingsystem.service.MovieSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {

    private final MovieSessionService movieSessionService;

    public MovieSessionController(MovieSessionService movieSessionService) {
        this.movieSessionService = movieSessionService;
    }

    @PostMapping("/add")
    public ResponseEntity<Long> addMovieSession(@RequestBody MovieSession movieSession) {
        Long id = movieSessionService.add(movieSession);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/available")
    public ResponseEntity<List<MovieSession>> findAvailableSessions(
            @RequestParam Long movieId,
            @RequestParam String date
    ) {
        LocalDate localDate = LocalDate.parse(date);
        List<MovieSession> sessions = movieSessionService.findAvailableSessions(movieId, localDate);
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieSession> getSessionById(@PathVariable Long id) {
        Optional<MovieSession> movieSession = movieSessionService.getById(id);
        return movieSession.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
