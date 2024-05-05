package com.example.bookingsystem.dto.auth;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {
    private String phoneNumber;

    private String password;
}
