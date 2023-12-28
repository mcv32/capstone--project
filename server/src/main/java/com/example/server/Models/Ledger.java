package com.example.server.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "ledger")
public class Ledger {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ledger_sequence")
    @SequenceGenerator(name = "ledger_seq", sequenceName = "LEDGER_SEQUENCE", allocationSize = 1)
    private Long ledger_id;
    private double amount;
    private boolean status;
    @ManyToOne
    @JoinColumn(
            nullable = false,
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
    @OneToMany(
            mappedBy = "ledger"
    )
    private List<TransactionTests> transactionTests = new ArrayList<>();
    private String description;
    private boolean recurring;
    private LocalDateTime recurringDate;

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

    public boolean getStatus() {
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

    public List<TransactionTests> getTransactionTests() {
        return transactionTests;
    }

    public void setTransactionTests(List<TransactionTests> transactionTests) {
        this.transactionTests = transactionTests;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public LocalDateTime getRecurringDate() {
        return recurringDate;
    }

    public void setRecurringDate(LocalDateTime recurringDate) {
        this.recurringDate = recurringDate;
    }

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
