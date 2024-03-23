package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CinemaRepository extends JpaRepository<CinemaEntity, Integer>, JpaSpecificationExecutor<CinemaEntity> {
}
