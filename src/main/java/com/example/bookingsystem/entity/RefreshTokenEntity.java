package com.example.bookingsystem.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@Table(name = "refresh_token")
public class RefreshTokenEntity  { @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "refresh_count")
    private Integer refreshCount;

    @Column(name = "expire_date", nullable = false)
    private Instant expiryDate;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    public void incrementRefreshCount() {
        refreshCount = refreshCount + 1;
    }
}
