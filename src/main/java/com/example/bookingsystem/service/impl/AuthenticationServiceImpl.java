package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.JwtUtils;
import com.example.bookingsystem.AuthenticationException;
import com.example.bookingsystem.dto.auth.AuthRequest;
import com.example.bookingsystem.dto.auth.AuthResponse;
import com.example.bookingsystem.dto.auth.RegistrationRequest;
import com.example.bookingsystem.entity.RefreshTokenEntity;
import com.example.bookingsystem.entity.UserEntity;
import com.example.bookingsystem.entity.enum_classes.UserRole;
import com.example.bookingsystem.repository.RefreshTokenRepository;
import com.example.bookingsystem.repository.UserRepository;
import com.example.bookingsystem.service.AuthenticationService;
import com.example.bookingsystem.service.RefreshTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;


@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    private final RefreshTokenRepository refreshTokenRepository;


    @Autowired
    public AuthenticationServiceImpl(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 JwtUtils jwtUtils,
                                 AuthenticationManager authenticationManager,
                                 RefreshTokenService refreshTokenService,
                                 RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public AuthResponse registrationUser(RegistrationRequest registerRequest) {

        UserEntity user = new UserEntity();


        user.setPhoneNumber(registerRequest.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setUserRole(UserRole.USER);
        user.setCreatedAt(Timestamp.from(Instant.now()).toLocalDateTime());
        user.setIsAccountExpired(Boolean.TRUE);
        user.setIsActive(Boolean.TRUE);
        user.setIsAccountLocked(Boolean.TRUE);
        user.setIsEnabled(Boolean.TRUE);

        userRepository.save(user);

        var jwtToken = jwtUtils.generateToken(user);
        var refreshToken = jwtUtils.generateRefreshToken(user);

        AuthResponse authenticationResponse = new AuthResponse(
                jwtToken,
                refreshToken
        );

        authenticationResponse.setToken(jwtToken);
        authenticationResponse.setRefreshToken(refreshToken);

        return authenticationResponse;
    }


    @Override
    public AuthResponse authentication(AuthRequest authenticationRequest) {

        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(authenticationRequest.getPhoneNumber(),
                authenticationRequest.getPassword()));

        UserEntity user = (UserEntity) authentication.getPrincipal();

        return createAndPersistRefreshTokenForDevice(authentication)
                .map(RefreshTokenEntity::getToken)
                .map(token -> {
                    String jwtToken = jwtUtils.generateToken(user);
                    String refreshToken = jwtUtils.generateToken(user);

                    AuthResponse authenticationResponse1 = new AuthResponse(jwtToken, refreshToken);

                    authenticationResponse1.setToken(jwtToken);
                    authenticationResponse1.setRefreshToken(refreshToken);

                    return authenticationResponse1;
                }).orElseThrow(() -> new AuthenticationException("Couldn't create refresh token for: " +
                        "[" + authenticationRequest + "]"));
    }


    @Override
    public Optional<RefreshTokenEntity> createAndPersistRefreshTokenForDevice(Authentication authentication) {
        UserEntity currentUser = (UserEntity) authentication.getPrincipal();
        RefreshTokenEntity refreshToken = refreshTokenService.createRefreshToken(currentUser);
        refreshToken = refreshTokenRepository.save(refreshToken);
        return Optional.of(refreshToken);
    }
}