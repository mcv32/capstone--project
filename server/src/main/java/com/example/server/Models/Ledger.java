package com.example.server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Ledger {
    @SequenceGenerator(
            name = "ledger_sequence",
            sequenceName = "ledger_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "ledger_sequence"
    )
    private int ledger_id;
    private double amount;
    private boolean status;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "account_id"
    )
    private FinancialAccount financialAccount;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "property_id"
    )
    private Property property;
    @OneToMany(
            mappedBy = "ledger"
    )
    private List<TransactionTests> transactionTests;
    private String description;
    private boolean recurring;
    private LocalDateTime recurringDate;

    public Ledger(double amount, boolean status, FinancialAccount financialAccount, Property property, List<TransactionTests> transactionTests, String description, boolean recurring, LocalDateTime recurringDate) {
        this.amount = amount;
        this.status = status;
        this.financialAccount = financialAccount;
        this.property = property;
        this.transactionTests = transactionTests;
        this.description = description;
        this.recurring = recurring;
        this.recurringDate = recurringDate;
    }
}
