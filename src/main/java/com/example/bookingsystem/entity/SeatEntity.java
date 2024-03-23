package com.example.bookingsystem.entity;

import com.example.bookingsystem.entity.enum_classes.StatusSeat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "seats")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "row")
    Integer row;
    @Column(name = "place")
    Integer place;
    @Column(name = "seat_status")
    StatusSeat statusSeat;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id")
    MovieSessionEntity session;
}
