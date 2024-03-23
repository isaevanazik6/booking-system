package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.CinemaRequest;
import com.example.bookingsystem.entity.CinemaEntity;

import java.util.List;
import java.util.Optional;

public interface CinemaService {
    Long createCinema(CinemaRequest cinema);
    List<CinemaEntity> getAllCinemas();
    Optional<CinemaRequest> getCinemaById(Long id);
    Long updateCinema(CinemaRequest updatedCinema);
    void deleteCinema(Long id);
}
