package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.RentRequest;

import java.util.Optional;

public interface RentService {
    Optional<RentRequest> getById(Long id);

    String rentPlace(Long id);

    String rollbackRent(Long id);
}
