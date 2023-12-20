package com.example.server.Services;

import com.example.server.Models.PaymentType;
import com.example.server.Models.Payments;
import com.example.server.Repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository){
        this.paymentsRepository = paymentsRepository;
    }

    public String processPayment(PaymentType paymentType, String number, String accountingNumber){
        Payments payments = new Payments(paymentType, number, accountingNumber);
        return payments.processPayment(paymentType, number, accountingNumber);
    }

}
