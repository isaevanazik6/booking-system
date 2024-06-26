package com.example.bookingsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSession {
    @NotNull(message = "'movieId' field cannot be empty!")
    @Min(value = 1, message = "Movie ID cannot be less than 1!")
    private Long movieId;

    @NotNull(message = "'cinemaHallId' field cannot be empty!")
    @Min(value = 1, message = "Cinema Hall ID cannot be less than 1!")
    private Long cinemaId;

    @NotNull(message = "'showTime' field cannot be empty!")
    @Future(message = "Showtime should be in the future!")
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private LocalDateTime showTime;
}