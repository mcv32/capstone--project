package com.example.server.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
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
    private Long account_id;
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String last_four_digits;
    private String encrypted_number;
    private LocalDateTime time;
    private boolean status;
    @OneToOne
    @JoinColumn(name = "ledger_id")
    @JsonBackReference
    private Ledger ledger;

    public TransactionTests(double amount, Long account_id, PaymentType paymentType, String last_four_digits, String encrypted_number, LocalDateTime time, boolean status) {
        this.amount = amount;
        this.account_id = account_id;
        this.paymentType = paymentType;
        this.last_four_digits = last_four_digits;
        this.encrypted_number = encrypted_number;
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

    public Long getAccount_id() {
        return account_id;
    }

    public void setAccount_id(Long account_id) {
        this.account_id = account_id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getLast_four_digits() {
        return last_four_digits;
    }

    public void setLast_four_digits(String last_four_digits) {
        this.last_four_digits = last_four_digits;
    }

    public String getEncrypted_number() {
        return encrypted_number;
    }

    public void setEncrypted_number(String encrypted_number) {
        this.encrypted_number = encrypted_number;
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

    public Ledger getLedger() {
        return ledger;
    }

    public void setLedger(Ledger ledger) {
        this.ledger = ledger;
    }
}
