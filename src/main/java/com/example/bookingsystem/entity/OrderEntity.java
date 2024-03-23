package com.example.bookingsystem.entity;

import com.example.bookingsystem.entity.enum_classes.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Timestamp;


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    @Column(name = "number_of_tickets")
    Integer numberOfTickets;
    @Column(name = "totalPrice")
    Double totalPrice;
    Timestamp created_at;
    Timestamp updated_at;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "session_id")
    MovieSessionEntity session;
}
