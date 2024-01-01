package com.example.server.REQUESTS;

import com.example.server.Models.PaymentType;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Data
@Builder
@Getter
@AllArgsConstructor
public class PaymentRequest {
    private PaymentType paymentType;
    private float amount;
    private String number;
    private String accountingNumber;
    private Long financial_account_id;
    private Long ledger_id;

}
