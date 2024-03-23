package com.example.bookingsystem.seat;

import com.example.bookingsystem.dto.MovieSession;
import com.example.bookingsystem.dto.Seat;
import com.example.bookingsystem.entity.SeatEntity;
import com.example.bookingsystem.entity.enum_classes.StatusSeat;
import com.example.bookingsystem.mapper.MovieSessionMapper;
import com.example.bookingsystem.mapper.SeatMapper;
import com.example.bookingsystem.repository.SeatRepository;
import com.example.bookingsystem.service.impl.MovieSessionServiceImpl;
import com.example.bookingsystem.service.impl.SeatServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SeatServiceImplTest {

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private MovieSessionServiceImpl movieSessionService;

    @Mock
    private MovieSessionMapper movieSessionMapper;

    @Mock
    private SeatMapper seatMapper;

    @InjectMocks
    private SeatServiceImpl seatService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddSeat() {
        // Mocking input data
        Seat seat = new Seat();
        seat.setRow(1);
        seat.setPlace(1);
        seat.setMovieSessionId(1L);

        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setId(1L);
        when(movieSessionService.getById(anyLong())).thenReturn(Optional.of(new MovieSession()));
        when(seatRepository.save(any())).thenReturn(seatEntity);

        // Calling the method
        Long id = seatService.add(seat);

        // Verifying the result
        assertEquals(1L, id);
    }

    @Test
    public void testGetSeatById() {
        // Mocking input data
        Long id = 1L;
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setId(id);
        when(seatMapper.toDto(any())).thenReturn(new Seat());
        when(seatRepository.findById(id)).thenReturn(Optional.of(seatEntity));

        // Calling the method
        var seatOptional = seatService.getById(id);

        // Verifying the result
        assertEquals(id, seatOptional.get().getMovieSessionId());
    }

    @Test
    public void testGetAllAvailableSeats() {
        // Mocking input data
        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setId(1L);
        when(seatMapper.toDto(any())).thenReturn(new Seat());
        when(seatRepository.getAllByAvailableStatus(StatusSeat.AVAILABLE))
                .thenReturn(Collections.singletonList(seatEntity));

        // Calling the method
        var seats = seatService.getAllByAvailableStatus();

        // Verifying the result
        assertEquals(1, seats.size());
    }

    @Test
    public void testUpdateSeat() {
        // Mocking input data
        Long seatId = 1L;
        Seat seat = new Seat();
        seat.setRow(2);
        seat.setPlace(2);
        seat.setMovieSessionId(1L);

        SeatEntity seatEntity = new SeatEntity();
        seatEntity.setId(seatId);
        when(seatRepository.findById(anyLong())).thenReturn(Optional.of(seatEntity));
        when(movieSessionService.getById(anyLong())).thenReturn(Optional.of(new MovieSession()));
        when(seatRepository.save(any())).thenReturn(seatEntity);

        // Calling the method
        Long updatedSeatId = seatService.updateSeat(seat, seatId);

        // Verifying the result
        assertEquals(seatId, updatedSeatId);
    }

    @Test
    public void testDeleteSeat() {
        // Mocking input data
        Long id = 1L;

        // Calling the method
        seatService.deleteSeat(id);

        // Verifying the method call
        verify(seatRepository, times(1)).deleteById(id);
    }
}

