package com.example.bookingsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "movie_sessions")
public class MovieSessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    CinemaEntity cinema;
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    MovieEntity movie;
    LocalDateTime showTime;
}
