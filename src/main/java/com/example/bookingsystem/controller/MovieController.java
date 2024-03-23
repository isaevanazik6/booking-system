package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.MovieRequest;
import com.example.bookingsystem.entity.MovieEntity;
import com.example.bookingsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<Long> createMovie(@RequestBody MovieRequest movie) {
        Long movieId = movieService.createMovie(movie);
        return new ResponseEntity<>(movieId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovieEntity>> getAllMovies() {
        List<MovieEntity> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieRequest> getMovieById(@PathVariable Long id) {
        Optional<MovieRequest> movieOptional = movieService.getMovieById(id);
        return movieOptional.map(movie -> ResponseEntity.ok().body(movie))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateMovie(@PathVariable Long id, @RequestBody MovieRequest movie) {
        movie.setId(id);
        Long updatedMovieId = movieService.updateMovie(movie);
        return ResponseEntity.ok(updatedMovieId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}

