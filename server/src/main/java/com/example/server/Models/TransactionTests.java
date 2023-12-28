package com.example.server.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name= "transaction_tests")
public class TransactionTests {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transactions_seq")
    @SequenceGenerator(name = "transactions_seq", sequenceName = "TRANSACTIONS_TESTS_SEQUENCE", allocationSize = 1)
    private Long transaction_id;
    private double amount;
    private Long financial_account_id;
    private Long ledger_id;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "financial_account"
    )
    @JsonBackReference
    private FinancialAccount financialAccount;
    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "ledger"
    )
    @JsonBackReference
    private Ledger ledger;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String cardNumber;
    private LocalDateTime time;
    private boolean status;

    public TransactionTests(Long transaction_id, double amount, Long financial_account_id, Long ledger_id, PaymentType paymentType, String cardNumber, LocalDateTime time, boolean status) {
        this.transaction_id = transaction_id;
        this.amount = amount;
        this.financial_account_id = financial_account_id;
        this.ledger_id = ledger_id;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
        this.time = time;
        this.status = status;
    }

    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getFinancial_account_id() {
        return financial_account_id;
    }

    public void setFinancial_account_id(Long financial_account_id) {
        this.financial_account_id = financial_account_id;
    }

    public Long getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(Long ledger_id) {
        this.ledger_id = ledger_id;
    }

    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
