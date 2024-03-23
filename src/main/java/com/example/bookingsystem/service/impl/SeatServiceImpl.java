package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.Seat;
import com.example.bookingsystem.entity.SeatEntity;
import com.example.bookingsystem.entity.enum_classes.StatusSeat;
import com.example.bookingsystem.mapper.MovieSessionMapper;
import com.example.bookingsystem.mapper.SeatMapper;
import com.example.bookingsystem.repository.SeatRepository;
import com.example.bookingsystem.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {

    private final SeatRepository seatRepository;
    private final MovieSessionServiceImpl movieSessionService;
    private final MovieSessionMapper movieSessionMapper;
    private final SeatMapper seatMapper;

    @Override
    public Long add(Seat seat) {
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setRow(seat.getRow());
        seatEntity.setPlace(seat.getPlace());
        var sessionMovie = movieSessionService
                .getById(seat.getMovieSessionId())
                .orElseThrow(() -> new RuntimeException("MovieSession with id " + seat.getMovieSessionId() + " not found"));
        seatEntity.setSession(movieSessionMapper.toEntity(sessionMovie));
        return seatRepository.save(seatEntity).getId();
    }

    @Override
    public Optional<Seat> getById(Long id) {
        return seatRepository.findById(id).map(seatMapper::toDto);
    }

    @Override
    public List<Seat> getAllByAvailableStatus() {
        return seatRepository
                .getAllByAvailableStatus(StatusSeat.AVAILABLE)
                .stream()
                .map(seatMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateSeat(Seat updatedSeat, Long seatId) {
        Optional<SeatEntity> existingSeatOptional = seatRepository.findById(seatId);
        if (existingSeatOptional.isPresent()) {
            SeatEntity existingSeat = existingSeatOptional.get();
            existingSeat.setRow(updatedSeat.getRow());
            existingSeat.setPlace(updatedSeat.getPlace());
            var sessionMovie = movieSessionService
                                .getById(updatedSeat.getMovieSessionId())
                                        .orElseThrow(() -> new RuntimeException("MovieSession with id " + seatId + " not found"));
            existingSeat.setSession(movieSessionMapper.toEntity(sessionMovie));
            return seatRepository.save(existingSeat).getId();
        } else {
            throw new RuntimeException("Movie with id " + seatId + " not found");
        }
    }

    @Override
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}
