package com.example.bookingsystem.service.impl;


import com.example.bookingsystem.dto.PaymentCheckRequest;
import com.example.bookingsystem.dto.PaymentRequest;
import com.example.bookingsystem.entity.PaymentEntity;
import com.example.bookingsystem.entity.PaymentStatus;
import com.example.bookingsystem.entity.StatusRent;
import com.example.bookingsystem.mapper.RentMapper;
import com.example.bookingsystem.repository.PaymentRepository;
import com.example.bookingsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final RentServiceImpl rentService;
    private final RentMapper rentMapper;


    @Override
    public String checkPayment(PaymentCheckRequest paymentCheckRequest) {

        var minValue = new BigDecimal(500);
        var maxValue = new BigDecimal(25000);

        if(paymentCheckRequest.getAmount().compareTo(minValue) < 0
                && paymentCheckRequest.getAmount().compareTo(maxValue) < 0
                && paymentCheckRequest.getAccountCheck() != null)  {

            PaymentEntity payment = new PaymentEntity();

            payment.setIsChecked(Boolean.TRUE);
            payment.setStatus(PaymentStatus.STATUS_PROCESS);

            paymentRepository.save(payment);

            return "Order is checked!";

        } else  {

            return "Error, you have problem with sumOfFavour or AccountCheck!";

        }
    }

    @Override
    public String addPayment(PaymentRequest paymentRequest, long id) {

        PaymentEntity payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Не был найден платеж с таким идентификатором!"));

        var rent = rentService
                .getById(paymentRequest.getRentId())
                .orElseThrow(() -> new RuntimeException("Не был найдена бронь с таким идентификатором!"));

        if(payment.getIsChecked().equals(Boolean.TRUE)) {

            payment.setStatus(PaymentStatus.STATUS_CREATED);
            payment.setCreated_at(Timestamp.from(Instant.now()));
            payment.setFinished(Boolean.FALSE);
            payment.setRent(rentMapper.toEntity(rent));
            payment.setSumOfFavour(paymentRequest.getPrice());
            payment.setAccountCheck(paymentRequest.getAccountCheck());

            paymentRepository.save(payment);

            return "Payment Created";

        } else {
            return "You have some with problem with AccountCheck or SumOfFavour";
        }
    }

    @Override
    public String setPayment(long id) {
        PaymentEntity payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Не был найден платеж с таким идентификатором!"));

        payment.setFinished(Boolean.TRUE);

        if(payment.getFinished() == Boolean.TRUE) {
            payment.setStatus(PaymentStatus.STATUS_SUCCESS);
            payment.getRent().setStatusRent(StatusRent.NOT_AVAILABLE);
        }

        payment.setUpdated_at(Timestamp.from(Instant.now()));

        paymentRepository.save(payment);

        return "Your status in payment: " + payment.getStatus();
    }

    @Override
    public String rollbackPayment(long id) {

        PaymentEntity payment = paymentRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Не был найден платеж с таким идентификатором!"));

        switch (payment.getStatus()) {
            case STATUS_SUCCESS -> {
                long Milli = Math.abs(payment.getUpdated_at().getTime() - new Date().getTime());

                if(Milli < 1080000) {
                    payment.setStatus(PaymentStatus.STATUS_ROLLBACK);
                    payment.setUpdated_at(Timestamp.from(Instant.now()));
                    paymentRepository.save(payment);
                    return "Payment Rollbacked";
                }
                else return "3 days gone";

            }
            case STATUS_CREATED -> {
                payment.setStatus(PaymentStatus.STATUS_ROLLBACK);
                payment.setUpdated_at(Timestamp.from(Instant.now()));
                paymentRepository.save(payment);
                return "Payment Rollbacked";
            }
            default -> {
                return "You don't have payment or your status fail";
            }
        }
    }
}