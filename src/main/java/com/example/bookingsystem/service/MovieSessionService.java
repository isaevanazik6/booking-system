package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.MovieSession;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovieSessionService {
    Long add(MovieSession movieSession);
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
    Optional<MovieSession> getById(Long id);
}
