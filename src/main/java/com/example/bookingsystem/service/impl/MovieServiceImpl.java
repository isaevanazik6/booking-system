package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.Movie;
import com.example.bookingsystem.entity.MovieEntity;
import com.example.bookingsystem.mapper.MovieMapper;
import com.example.bookingsystem.repository.MovieRepository;
import com.example.bookingsystem.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieEntityRepository;
    private final MovieMapper mapper;

    // Create
    @Override
    public Long createMovie(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setName(movie.getName());
        movieEntity.setYearOfCreation(movie.getYearOfCreation());
        movieEntity.setDirector(movie.getDirector());
        movieEntity.setDuration(movie.getDuration());
        movieEntity.setCountry(movie.getCountry());
        movieEntity.setDescription(movie.getDescription());
        return movieEntityRepository.save(movieEntity).getId();
    }

    // Read
    @Override
    public List<MovieEntity> getAllMovies() {
        return movieEntityRepository.findAll();
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieEntityRepository
                .findById(id)
                .map(mapper::toDto);
    }

    // Update
    @Override
    public Long updateMovie(Movie updatedMovie, Long movieId) {
        Optional<MovieEntity> existingMovieOptional = movieEntityRepository.findById(movieId);
        if (existingMovieOptional.isPresent()) {
            MovieEntity existingMovie = existingMovieOptional.get();
            existingMovie.setName(updatedMovie.getName());
            existingMovie.setYearOfCreation(updatedMovie.getYearOfCreation());
            existingMovie.setDirector(updatedMovie.getDirector());
            existingMovie.setDuration(updatedMovie.getDuration());
            existingMovie.setCountry(updatedMovie.getCountry());
            existingMovie.setDescription(updatedMovie.getDescription());
            return movieEntityRepository.save(existingMovie).getId();
        } else {
            throw new RuntimeException("Movie with id " + movieId + " not found");
        }
    }

    // Delete
    @Override
    public void deleteMovie(Long id) {
        movieEntityRepository.deleteById(id);
    }
}

