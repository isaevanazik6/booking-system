package com.example.bookingsystem.dto.auth;

import lombok.*;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
}