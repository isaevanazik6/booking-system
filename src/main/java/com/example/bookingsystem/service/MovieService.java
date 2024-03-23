package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.MovieRequest;
import com.example.bookingsystem.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Long createMovie(MovieRequest movie);
    List<MovieEntity> getAllMovies();
    Optional<MovieRequest> getMovieById(Long id);
    Long updateMovie(MovieRequest updatedMovie);
    void deleteMovie(Long id);
}
