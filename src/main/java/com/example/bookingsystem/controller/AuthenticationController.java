package com.example.bookingsystem.controller;

import com.example.bookingsystem.dto.auth.AuthRequest;
import com.example.bookingsystem.dto.auth.AuthResponse;
import com.example.bookingsystem.dto.auth.RegistrationRequest;
import com.example.bookingsystem.service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegistrationRequest registerRequest) {
        return ResponseEntity.status(201).body(authenticationService.registrationUser(registerRequest));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest authenticationRequest) {
        return ResponseEntity.status(201).body(authenticationService.authentication(authenticationRequest));
    }
}