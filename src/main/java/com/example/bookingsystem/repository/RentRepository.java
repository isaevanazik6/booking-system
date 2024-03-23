package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.RentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RentRepository extends JpaRepository<RentEntity, Long>, JpaSpecificationExecutor<RentEntity> {
}
