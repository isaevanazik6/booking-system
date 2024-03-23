package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Query(value = "SELECT * FROM wallet.payments WHERE wallet.payments.status = ?1", nativeQuery = true)
    List<OrderEntity> getByStatus(@Param("status") String status);
}
