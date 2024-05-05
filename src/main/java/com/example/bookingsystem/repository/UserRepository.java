package com.example.bookingsystem.repository;

import com.example.bookingsystem.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByPhoneNumber(String phoneNumber);
}
