package com.example.bookingsystem.mapper;

import com.example.bookingsystem.dto.Movie;
import com.example.bookingsystem.entity.MovieEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {
    Movie toDto(MovieEntity movieEntity);
}
