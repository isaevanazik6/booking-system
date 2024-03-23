package com.example.bookingsystem.service.impl;

import com.example.bookingsystem.dto.Order;
import com.example.bookingsystem.entity.OrderEntity;
import com.example.bookingsystem.entity.enum_classes.OrderStatus;
import com.example.bookingsystem.mapper.MovieSessionMapper;
import com.example.bookingsystem.repository.OrderRepository;
import com.example.bookingsystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MovieSessionServiceImpl movieSessionService;
    private final MovieSessionMapper movieSessionMapper;
    private final OrderRepository orderRepository;


    @Override
    public Long addOrder(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        var sessionMovie = movieSessionService
                .getById(order.getSessionId())
                .orElseThrow(() -> new RuntimeException("MovieSession with id " + order.getSessionId() + " not found"));
        orderEntity.setStatus(OrderStatus.STATUS_CREATED);
        orderEntity.setCreated_at(Timestamp.from(Instant.now()));
        orderEntity.setTotalPrice(order.getTotalPrice());
        orderEntity.setNumberOfTickets(order.getNumberOfTickets());
        orderEntity.setSession(movieSessionMapper.toEntity(sessionMovie));

        return orderRepository.save(orderEntity).getId();
    }

    @Override
    public String confirmOrder(Long id) {
        OrderEntity payment = orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with this identifier " + id +  " was not found!"));

        payment.setStatus(OrderStatus.STATUS_SUCCESS);
        payment.setUpdated_at(Timestamp.from(Instant.now()));

        orderRepository.save(payment);

        return "Статус платежа: " + payment.getStatus();
    }

    @Override
    public String rollbackPayment(Long id) {
        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Payment with this identifier " + id +  " was not found!"));

        switch (order.getStatus()) {
            case STATUS_SUCCESS -> {
                long Milli = Math.abs(order.getUpdated_at().getTime() - new Date().getTime());

                if(Milli < 1080000) {
                    order.setStatus(OrderStatus.STATUS_ROLLBACK);
                    order.setUpdated_at(Timestamp.from(Instant.now()));
                    orderRepository.save(order);
                    return "Payment Rollbacked";
                }
                else return "3 days gone";

            }
            case STATUS_CREATED -> {
                order.setStatus(OrderStatus.STATUS_ROLLBACK);
                order.setUpdated_at(Timestamp.from(Instant.now()));
                orderRepository.save(order);
                return "Payment Rollbacked";
            }
            default -> {
                return "You don't have payment or your status fail";
            }
        }
    }
}
