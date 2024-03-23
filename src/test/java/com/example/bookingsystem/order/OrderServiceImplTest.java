package com.example.bookingsystem.order;

import com.example.bookingsystem.dto.MovieSession;
import com.example.bookingsystem.dto.Order;
import com.example.bookingsystem.entity.OrderEntity;
import com.example.bookingsystem.entity.enum_classes.OrderStatus;
import com.example.bookingsystem.mapper.MovieSessionMapper;
import com.example.bookingsystem.repository.OrderRepository;
import com.example.bookingsystem.service.impl.MovieSessionServiceImpl;
import com.example.bookingsystem.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

    @Mock
    private MovieSessionServiceImpl movieSessionService;

    @Mock
    private MovieSessionMapper movieSessionMapper;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddOrder() {
        // Mocking input data
        Order order = new Order();
        order.setSessionId(1L);
        order.setTotalPrice(100d);
        order.setNumberOfTickets(2);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1L);
        when(movieSessionService.getById(1L)).thenReturn(Optional.of(new MovieSession()));
        when(orderRepository.save(any())).thenReturn(orderEntity);

        // Calling the method
        Long id = orderService.addOrder(order);

        // Verifying the result
        assertEquals(1L, id);
    }

    @Test
    public void testConfirmOrder() {
        // Mocking input data
        Long id = 1L;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setStatus(OrderStatus.STATUS_CREATED);
        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));

        // Calling the method
        String result = orderService.confirmOrder(id, 1L);

        // Verifying the result
        assertEquals("Статус платежа: " + OrderStatus.STATUS_SUCCESS, result);
    }

    @Test
    public void testRollbackPayment() {
        // Mocking input data
        Long id = 1L;
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(id);
        orderEntity.setStatus(OrderStatus.STATUS_CREATED);
        orderEntity.setUpdated_at(Timestamp.from(Instant.now()));
        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));

        // Calling the method
        String result = orderService.rollbackPayment(id, 1L);

        // Verifying the result
        assertEquals("Payment Rollbacked", result);
    }
}

