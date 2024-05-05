package com.example.bookingsystem.service;

import com.example.bookingsystem.entity.RefreshTokenEntity;
import com.example.bookingsystem.entity.UserEntity;

public interface RefreshTokenService {
    void deleteById(int id);

    RefreshTokenEntity createRefreshToken(UserEntity user);
}
