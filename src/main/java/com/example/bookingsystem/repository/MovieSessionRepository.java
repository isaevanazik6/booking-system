package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.MovieSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MovieSessionRepository extends JpaRepository<MovieSessionEntity, Long> {
    List<MovieSessionEntity> findByMovieIdAndShowTimeBetween(Long movieId, LocalDateTime from, LocalDateTime until);
}
