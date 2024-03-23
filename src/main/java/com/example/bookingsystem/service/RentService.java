package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.Rent;

import java.util.Optional;

public interface RentService {
    public Optional<Rent> getById(Long id);

    public String addRent(Long id);

    public String rollbackRent(Long id);
}
