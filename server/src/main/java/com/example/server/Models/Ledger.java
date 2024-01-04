package com.example.server.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ledger")
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger_seq")
    @SequenceGenerator(name = "ledger_seq", sequenceName = "LEDGER_SEQUENCE", allocationSize = 1)
    private Long ledger_id;
    private double amount;
    private boolean status;
    @ManyToOne
    @JoinColumn(
            nullable = true,
            name = "financial_account_id"
    )
    @JsonBackReference
    private FinancialAccount financialAccount;
    @OneToOne
    @JoinColumn(
            nullable = false,
            name = "property_id"
    )
    private Property property;
    @OneToOne(
            mappedBy = "ledger", optional = true
    )
    private TransactionTests transactionTests;
    private String description;
    private LocalDateTime time;
    private LedgerType ledgerType;

    public Ledger(double amount, boolean status, FinancialAccount financialAccount, Property property, String description, LocalDateTime time, LedgerType ledgerType) {
        this.amount = amount;
        this.status = status;
        this.financialAccount = financialAccount;
        this.property = property;
        this.description = description;
        this.time = time;
        this.ledgerType = ledgerType;
    }

    public Long getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(Long ledger_id) {
        this.ledger_id = ledger_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public TransactionTests getTransactionTests() {
        return transactionTests;
    }

    public void setTransactionTests(TransactionTests transactionTests) {
        this.transactionTests = transactionTests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public LedgerType getLedgerType() {
        return ledgerType;
    }

    public void setLedgerType(LedgerType ledgerType) {
        this.ledgerType = ledgerType;
    }
}
