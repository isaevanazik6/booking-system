package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.JwtUtils;
import com.example.bookingsystem.entity.RefreshTokenEntity;
import com.example.bookingsystem.entity.UserEntity;
import com.example.bookingsystem.repository.RefreshTokenRepository;
import com.example.bookingsystem.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtUtils jwtUtils;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private int refreshTokenDurationMs;


    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtUtils jwtUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public void deleteById(int id) {
        refreshTokenRepository.deleteById(id);
    }


    @Override
    public RefreshTokenEntity createRefreshToken(UserEntity user) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();

        var refreshTokens = jwtUtils.generateRefreshToken(user);

        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(refreshTokens);
        refreshToken.setRefreshCount(0);
        refreshToken.setCreatedAt(Instant.now());
        refreshToken.setUpdatedAt(Instant.now());

        refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }
}