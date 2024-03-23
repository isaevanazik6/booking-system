package com.example.bookingsystem.movie_session;

import com.example.bookingsystem.dto.MovieSession;
import com.example.bookingsystem.entity.CinemaEntity;
import com.example.bookingsystem.entity.MovieEntity;
import com.example.bookingsystem.entity.MovieSessionEntity;
import com.example.bookingsystem.mapper.CinemaMapper;
import com.example.bookingsystem.mapper.MovieMapper;
import com.example.bookingsystem.mapper.MovieSessionMapper;
import com.example.bookingsystem.repository.MovieSessionRepository;
import com.example.bookingsystem.service.impl.CinemaServiceImpl;
import com.example.bookingsystem.service.impl.MovieServiceImpl;
import com.example.bookingsystem.service.impl.MovieSessionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MovieSessionServiceImplTest {

    @Mock
    private MovieSessionRepository movieSessionRepository;

    @Mock
    private MovieServiceImpl movieService;

    @Mock
    private CinemaServiceImpl cinemaService;

    @Mock
    private MovieMapper movieMapper;

    @Mock
    private CinemaMapper cinemaMapper;

    @Mock
    private MovieSessionMapper movieSessionMapper;

    @InjectMocks
    private MovieSessionServiceImpl movieSessionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddMovieSession() {
        // Mocking input data
        MovieSession movieSession = new MovieSession();
        movieSession.setMovieId(1L);
        movieSession.setCinemaId(1L);
        movieSession.setShowTime(LocalDateTime.now());

        MovieSessionEntity movieSessionEntity = new MovieSessionEntity();
        movieSessionEntity.setId(1L);
        when(movieMapper.toEntity(any())).thenReturn(new MovieEntity());
        when(cinemaMapper.toEntity(any())).thenReturn(new CinemaEntity());
        when(movieSessionRepository.save(any())).thenReturn(movieSessionEntity);

        // Calling the method
        Long id = movieSessionService.add(movieSession);

        // Verifying the result
        assertEquals(1L, id);
    }

    @Test
    public void testFindAvailableSessions() {
        // Mocking input data
        Long movieId = 1L;
        LocalDate date = LocalDate.now();
        LocalDateTime from = date.atStartOfDay();
        LocalDateTime until = date.atTime(23, 59, 59);

        MovieSessionEntity movieSessionEntity = new MovieSessionEntity();
        movieSessionEntity.setId(1L);
        when(movieSessionMapper.toDto(any())).thenReturn(new MovieSession());
        when(movieSessionRepository.findByMovieIdAndShowTimeBetween(movieId, from, until))
                .thenReturn(Collections.singletonList(movieSessionEntity));

        // Calling the method
        var sessions = movieSessionService.findAvailableSessions(movieId, date);

        // Verifying the result
        assertEquals(1, sessions.size());
    }

    @Test
    public void testGetMovieSessionById() {
        // Mocking input data
        Long id = 1L;
        MovieSessionEntity movieSessionEntity = new MovieSessionEntity();
        movieSessionEntity.setId(id);
        when(movieSessionMapper.toDto(any())).thenReturn(new MovieSession());
        when(movieSessionRepository.findById(id)).thenReturn(Optional.of(movieSessionEntity));

        // Calling the method
        var movieSessionOptional = movieSessionService.getById(id);

        // Verifying the result
        assertEquals(id, movieSessionOptional.get().getMovieId());
    }
}

