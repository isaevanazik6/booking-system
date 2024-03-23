package com.example.bookingsystem.mapper;

import com.example.bookingsystem.dto.Rent;
import com.example.bookingsystem.entity.RentEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RentMapper {
    Rent toDto(RentEntity rentEntity);
    RentEntity toEntity(Rent rent);
}
