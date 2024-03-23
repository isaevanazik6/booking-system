package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}
