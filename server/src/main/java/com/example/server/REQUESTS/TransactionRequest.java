package com.example.server.REQUESTS;

import com.example.server.Models.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@AllArgsConstructor
public class TransactionRequest {
    private float amount;
    private Long financial_account_id;
    private Long ledger_id;
    private PaymentType paymentType;
    private String cardNumber;
    private LocalDateTime time;
    private boolean status;
}
