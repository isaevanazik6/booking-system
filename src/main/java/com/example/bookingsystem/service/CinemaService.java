package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.Cinema;
import com.example.bookingsystem.entity.CinemaEntity;

import java.util.List;
import java.util.Optional;

public interface CinemaService {
    Long createCinema(Cinema cinema);
    List<CinemaEntity> getAllCinemas();
    Optional<Cinema> getCinemaById(Long id);
    Long updateCinema(Cinema updatedCinema);
    void deleteCinema(Long id);
}
