package com.example.bookingsystem.mapper;

import com.example.bookingsystem.dto.Seat;
import com.example.bookingsystem.entity.SeatEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SeatMapper {
    Seat toDto(SeatEntity seatEntity);

    SeatEntity toEntity(Seat seat);

    default List<Seat> toListDto(List<SeatEntity> seatEntities) {
        return seatEntities
                .stream()
                .map(seatEntity -> {
                    Seat seat = new Seat();
                    seat.setRow(seatEntity.getRow());
                    seat.setPlace(seatEntity.getPlace());
                    seat.setMovieSessionId(seatEntity.getSession().getId());
                    return seat;
                }).collect(Collectors.toList());
    }
}
