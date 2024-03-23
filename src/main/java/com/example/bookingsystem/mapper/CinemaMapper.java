package com.example.bookingsystem.mapper;

import com.example.bookingsystem.dto.Cinema;
import com.example.bookingsystem.entity.CinemaEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CinemaMapper {
    Cinema toDto(CinemaEntity cinemaEntity);
    CinemaEntity toEntity(Cinema cinema);
}
