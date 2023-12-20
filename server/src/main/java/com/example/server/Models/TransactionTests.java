package com.example.server.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "transaction_tests")
public class TransactionTests {
    @SequenceGenerator(
            name = "transaction_tests_sequence",
            sequenceName = "transaction_tests_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_tests_sequence"
    )
    private int transaction_id;
    private double amount;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "financial_account_id"
    )
    private FinancialAccount financialAccount;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "ledger_id"
    )
    private Ledger ledger;

    public TransactionTests( double amount, FinancialAccount financialAccount, Ledger ledger) {
        this.amount = amount;
        this.financialAccount = financialAccount;
        this.ledger = ledger;
    }
}
