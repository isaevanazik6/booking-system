package com.example.bookingsystem.mapper;

import com.example.bookingsystem.dto.MovieRequest;
import com.example.bookingsystem.entity.MovieEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {
    MovieRequest toDto(MovieEntity movieEntity);
}
