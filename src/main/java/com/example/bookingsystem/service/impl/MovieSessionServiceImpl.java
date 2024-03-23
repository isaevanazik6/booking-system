package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.MovieSession;
import com.example.bookingsystem.entity.MovieSessionEntity;
import com.example.bookingsystem.mapper.CinemaMapper;
import com.example.bookingsystem.mapper.MovieMapper;
import com.example.bookingsystem.mapper.MovieSessionMapper;
import com.example.bookingsystem.repository.MovieSessionRepository;
import com.example.bookingsystem.service.MovieSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieSessionServiceImpl implements MovieSessionService {
    private final MovieSessionRepository movieSessionRepository;
    private final MovieServiceImpl movieService;
    private final CinemaServiceImpl cinemaService;
    private final MovieMapper movieMapper;
    private final CinemaMapper cinemaMapper;
    private final MovieSessionMapper movieSessionMapper;


    @Override
    public Long add(MovieSession movieSession) {
        MovieSessionEntity movieSessionEntity = new MovieSessionEntity();
        var movie = movieService
                            .getMovieById(movieSession.getMovieId())
                            .orElseThrow(() -> new RuntimeException("Movie with this id " + movieSession.getMovieId() + " not found!"));
        movieSessionEntity.setMovie(movieMapper.toEntity(movie));

        var cinema = cinemaService
                            .getCinemaById(movieSession.getCinemaId())
                            .orElseThrow(() -> new RuntimeException("Cinema with this id " + movieSession.getMovieId() + " not found!"));
        movieSessionEntity.setCinema(cinemaMapper.toEntity(cinema));
        movieSessionEntity.setShowTime(movieSession.getShowTime());
        return movieSessionRepository.save(movieSessionEntity).getId();
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime until = date.atTime(LocalTime.MAX);
        return movieSessionRepository
                .findByMovieIdAndShowTimeBetween(movieId, from, until)
                .stream()
                .map(movieSessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MovieSession> getById(Long id) {
        return movieSessionRepository
                                .findById(id)
                                .map(movieSessionMapper::toDto);
    }
}
