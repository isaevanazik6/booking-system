package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.Cinema;
import com.example.bookingsystem.entity.CinemaEntity;
import com.example.bookingsystem.mapper.CinemaMapper;
import com.example.bookingsystem.repository.CinemaRepository;
import com.example.bookingsystem.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public Long createCinema(Cinema cinema) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setName(cinema.getName());
        return cinemaRepository.save(cinemaEntity).getId();
    }

    @Override
    public List<CinemaEntity> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Optional<Cinema> getCinemaById(Long id) {
        return cinemaRepository
                .findById(id)
                .map(cinemaMapper::toDto);
    }

    @Override
    public Long updateCinema(Cinema updatedCinema) {
        Optional<CinemaEntity> existingMovieOptional = cinemaRepository.findById(updatedCinema.getId());
        if (existingMovieOptional.isPresent()) {
            CinemaEntity existingMovie = existingMovieOptional.get();
            existingMovie.setName(updatedCinema.getName());
            return cinemaRepository.save(existingMovie).getId();
        } else {
            throw new RuntimeException("Movie with id " + updatedCinema.getId() + " not found");
        }
    }

    @Override
    public void deleteCinema(Long id) {
        cinemaRepository.deleteById(id);
    }
}
