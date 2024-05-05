package com.example.bookingsystem.service;

import com.example.bookingsystem.dto.auth.AuthRequest;
import com.example.bookingsystem.dto.auth.AuthResponse;
import com.example.bookingsystem.dto.auth.RegistrationRequest;
import com.example.bookingsystem.entity.RefreshTokenEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface AuthenticationService {
    AuthResponse registrationUser(RegistrationRequest registerRequest);
    AuthResponse authentication(AuthRequest authenticationRequest);
    Optional<RefreshTokenEntity> createAndPersistRefreshTokenForDevice(Authentication authentication);
}
