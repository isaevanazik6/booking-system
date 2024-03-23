package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.RentRequest;
import com.example.bookingsystem.entity.StatusRent;
import com.example.bookingsystem.mapper.RentMapper;
import com.example.bookingsystem.repository.RentRepository;
import com.example.bookingsystem.service.RentService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.example.bookingsystem.entity.StatusRent.IN_PROGRESS;


@RequiredArgsConstructor
@Transactional
@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;

    @Override
    public Optional<RentRequest> getById(Long id) {

        return rentRepository
                .findById(id)
                .map(rentMapper::toDto);
    }

    @Override
    public String rentPlace(Long id) {

        var rent = rentRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("RentRequest not found!"));

        switch (rent.getStatusRent()) {
            case IN_PROGRESS -> {
                return "RentRequest is in progress";
            }
            case NOT_AVAILABLE -> {
                return "RentRequest is in been not free";
            }
            default -> {
                rent.setStatusRent(IN_PROGRESS);
                rentRepository.save(rent);
                return "Status in Progress";
            }
        }
    }

    @Override
    public String rollbackRent(Long id) {

        var rent = rentRepository
                                .findById(id)
                                .orElseThrow(() -> new RuntimeException("RentRequest not found!"));

        if(rent.getStatusRent().equals(IN_PROGRESS)) {
            rent.setIsRent(Boolean.TRUE);
            rent.setStatusRent(StatusRent.AVAILABLE);
        }

        return "Rollback RentRequest";
    }
}