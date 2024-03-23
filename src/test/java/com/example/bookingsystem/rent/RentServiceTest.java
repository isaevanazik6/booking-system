package com.example.bookingsystem.rent;

import com.example.bookingsystem.dto.RentRequest;
import com.example.bookingsystem.entity.StatusRent;
import com.example.bookingsystem.mapper.RentMapper;
import com.example.bookingsystem.repository.RentRepository;
import com.example.bookingsystem.service.impl.RentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.example.bookingsystem.entity.StatusRent.IN_PROGRESS;
import static com.example.bookingsystem.entity.StatusRent.NOT_AVAILABLE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RentServiceTest {

    @Mock
    private RentRepository rentRepository;

    @Mock
    private RentMapper rentMapper;

    @InjectMocks
    private RentServiceImpl rentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById() {
        // Создание mock объекта RentRequest
        RentRequest mockRent = new RentRequest();
        mockRent.setId(1L);
        when(rentRepository.findById(1L)).thenReturn(Optional.of(rentMapper.toEntity(mockRent)));

        // Вызов метода для тестирования
        Optional<RentRequest> result = rentService.getById(1L);

        // Проверка результата
        assertEquals(1L, result.get().getId());
        // Можно добавить дополнительные проверки, если необходимо
    }

    @Test
    public void testAddRentInProgress() {
        // Создание mock объекта RentRequest
        RentRequest mockRent = new RentRequest();
        mockRent.setStatusRent(IN_PROGRESS);
        when(rentRepository.findById(anyLong())).thenReturn(Optional.of(rentMapper.toEntity(mockRent)));

        // Вызов метода для тестирования
        String result = rentService.rentPlace(1L);

        // Проверка результата
        assertEquals("RentRequest is in progress", result);
        verify(rentRepository, never()).save(any());
    }

    @Test
    public void testAddRentNotAvailable() {
        // Создание mock объекта RentRequest
        RentRequest mockRent = new RentRequest();
        mockRent.setStatusRent(NOT_AVAILABLE);
        when(rentRepository.findById(anyLong())).thenReturn(Optional.of(rentMapper.toEntity(mockRent)));

        // Вызов метода для тестирования
        String result = rentService.rentPlace(1L);

        // Проверка результата
        assertEquals("RentRequest is in been not free", result);
        verify(rentRepository, never()).save(any());
    }

    @Test
    public void testAddRentSuccess() {
        // Создание mock объекта RentRequest
        RentRequest mockRent = new RentRequest();
        mockRent.setStatusRent(StatusRent.AVAILABLE);
        when(rentRepository.findById(anyLong())).thenReturn(Optional.of(rentMapper.toEntity(mockRent)));
        when(rentRepository.save(any())).thenReturn(mockRent);

        // Вызов метода для тестирования
        String result = rentService.rentPlace(1L);

        // Проверка результата
        assertEquals("Status in Progress", result);
        verify(rentRepository, times(1)).save(any());
    }

    @Test
    public void testRollbackRentInProgress() {
        // Создание mock объекта RentRequest
        RentRequest mockRent = new RentRequest();
        mockRent.setStatusRent(IN_PROGRESS);
        when(rentRepository.findById(anyLong())).thenReturn(Optional.of(rentMapper.toEntity(mockRent)));

        // Вызов метода для тестирования
        String result = rentService.rollbackRent(1L);

        // Проверка результата
        assertEquals("Rollback RentRequest", result);
        assertEquals(StatusRent.AVAILABLE, mockRent.getStatusRent());
        assertTrue(mockRent.getIsRent());
        verify(rentRepository, times(1)).save(rentMapper.toEntity(mockRent));
    }

    @Test
    public void testRollbackRentNotInProgress() {
        // Создание mock объекта RentRequest
        RentRequest mockRent = new RentRequest();
        mockRent.setStatusRent(NOT_AVAILABLE);
        when(rentRepository.findById(anyLong())).thenReturn(Optional.of(rentMapper.toEntity(mockRent)));

        // Вызов метода для тестирования
        String result = rentService.rollbackRent(1L);

        // Проверка результата
        assertEquals("Rollback RentRequest", result); // Можно либо возвращать статус, либо бросать исключение, в зависимости от требований
        verify(rentRepository, never()).save(any());
    }
}

