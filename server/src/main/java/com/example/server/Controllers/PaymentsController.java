package com.example.server.Controllers;

import com.example.server.Models.PaymentType;
import com.example.server.Models.Payments;
import com.example.server.Services.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping(path = "/payments")
public class PaymentsController {
    private final PaymentsService paymentsService;

    @Autowired
    public PaymentsController(PaymentsService paymentsService){
        this.paymentsService = paymentsService;
    }

    @PostMapping
    public String processPayment(@RequestBody Payments payments){
        return paymentsService.processPayment(payments.getPaymentType(), payments.getNumber(), String.valueOf(Optional.ofNullable(payments.getAccountingNumber())));
    }
}
