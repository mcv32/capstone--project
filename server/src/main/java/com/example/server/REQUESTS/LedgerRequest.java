package com.example.server.REQUESTS;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Data
@Builder
@Getter
@AllArgsConstructor
public class LedgerRequest {
    private float amount;
    private String description;
    private Long financial_account_id;
    private Long property_id;
    private boolean recurring;
    private LocalDateTime recurring_date;
    private boolean status;
}
