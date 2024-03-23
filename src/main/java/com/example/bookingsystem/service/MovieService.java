package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.Movie;
import com.example.bookingsystem.entity.MovieEntity;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Long createMovie(Movie movie);
    List<MovieEntity> getAllMovies();
    Optional<Movie> getMovieById(Long id);
    Long updateMovie(Movie updatedMovie);
    void deleteMovie(Long id);
}
