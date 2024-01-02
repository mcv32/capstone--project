package com.example.server.Services;

import com.example.server.Exceptions.PaymentInfoInvalid;
import com.example.server.Models.PaymentType;
import com.example.server.Models.Payments;
import com.example.server.Models.TransactionTests;
import com.example.server.REQUESTS.PaymentRequest;
import com.example.server.REQUESTS.TransactionRequest;
import com.example.server.Repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentsService {

    @Autowired
    private final PaymentsRepository paymentsRepository;
    @Autowired
    private final TransactionTestsService transactionTestsService;

    @Autowired
    public PaymentsService(PaymentsRepository paymentsRepository, TransactionTestsService transactionTestsService){
        this.paymentsRepository = paymentsRepository;
        this.transactionTestsService = transactionTestsService;
    }

    public String processPayment(PaymentRequest paymentRequest){
        Payments payments = new Payments(paymentRequest.getPaymentType(),
                paymentRequest.getNumber(),
                paymentRequest.getAccountingNumber()
        );

        String success = payments.processPayment(payments.getPaymentType(), payments.getNumber(), payments.getAccountingNumber());
        System.out.println(success);
        if(success.equals("unsuccessful")){
            throw new PaymentInfoInvalid("Unsuccessful payment: Invalid Info");

        }

        TransactionRequest transactionRequest = new TransactionRequest(
                paymentRequest.getAmount(),
                paymentRequest.getFinancial_account_id(),
                paymentRequest.getProperty_id(),
                paymentRequest.getPaymentType(),
                paymentRequest.getNumber(),
                LocalDateTime.now(),
                true
        );
        return transactionTestsService.createTransactionTest(transactionRequest);

    }

}
