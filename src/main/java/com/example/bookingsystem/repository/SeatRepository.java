package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.SeatEntity;
import com.example.bookingsystem.entity.enum_classes.StatusSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

    @Query("SELECT s FROM SeatEntity s WHERE s.statusSeat = ?1")
    List<SeatEntity> getAllByAvailableStatus(StatusSeat statusSeat);
}
