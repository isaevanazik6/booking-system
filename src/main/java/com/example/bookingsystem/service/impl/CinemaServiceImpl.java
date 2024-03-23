package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.CinemaRequest;
import com.example.bookingsystem.entity.CinemaEntity;
import com.example.bookingsystem.mapper.CinemaMapper;
import com.example.bookingsystem.repository.CinemaRepository;
import com.example.bookingsystem.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;
    private final CinemaMapper cinemaMapper;

    @Override
    public Long createCinema(CinemaRequest cinema) {
        CinemaEntity cinemaEntity = new CinemaEntity();
        cinemaEntity.setName(cinema.getName());
        return cinemaRepository.save(cinemaEntity).getId();
    }

    @Override
    public List<CinemaEntity> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public Optional<CinemaRequest> getCinemaById(Long id) {
        return cinemaRepository
                .findById(id)
                .map(cinemaMapper::toDto);
    }

    @Override
    public Long updateCinema(CinemaRequest updatedCinema) {
        Optional<CinemaEntity> existingMovieOptional = cinemaRepository.findById(updatedCinema.getId());
        if (existingMovieOptional.isPresent()) {
            CinemaEntity existingMovie = existingMovieOptional.get();
            existingMovie.setName(updatedCinema.getName());
            return cinemaRepository.save(existingMovie).getId();
        } else {
            throw new RuntimeException("MovieRequest with id " + updatedCinema.getId() + " not found");
        }
    }

    @Override
    public void deleteCinema(Long id) {
        cinemaRepository.deleteById(id);
    }
}
