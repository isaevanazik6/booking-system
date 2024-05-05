package com.example.bookingsystem.mapper;

import com.example.bookingsystem.dto.MovieSession;
import com.example.bookingsystem.entity.MovieSessionEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieSessionMapper {
    default MovieSession toDto(MovieSessionEntity movieSessionEntity) {
        MovieSession movieSession = new MovieSession();
        movieSession.setShowTime(movieSessionEntity.getShowTime());
        movieSession.setCinemaId(movieSessionEntity.getCinema().getId());
        movieSession.setMovieId(movieSessionEntity.getMovie().getId());

        return movieSession;
    };

    MovieSessionEntity toEntity(MovieSession movieSession);

    default List<MovieSession> toListDto(List<MovieSessionEntity> movieSessionEntities) {
        return movieSessionEntities
                .stream()
                .map(movieSessionEntity -> {
            MovieSession movieSession = new MovieSession();
            movieSession.setMovieId(movieSessionEntity.getMovie().getId());
            movieSession.setCinemaId(movieSessionEntity.getCinema().getId());
            movieSession.setShowTime(movieSessionEntity.getShowTime());
            return movieSession;
        }).collect(Collectors.toList());
    }
}
