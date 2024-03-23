package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.Movie;
import com.example.bookingsystem.dto.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatService {
    Long add(Seat seat);
    Optional<Seat> getById(Long id);
    List<Seat> getAllByAvailableStatus();
    Long updateSeat(Seat updatedSeat, Long seatId);
    void deleteSeat(Long id);
}
