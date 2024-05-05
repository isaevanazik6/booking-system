package com.example.bookingsystem.dto.auth;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
}
