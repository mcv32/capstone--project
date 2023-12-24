package com.example.server.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Setter
public class Payments {

    @Id
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    private String number;
    private String accountingNumber;
    private HashSet<String> validCards = new HashSet<>();

    private HashSet<String> validACHeChecks = new HashSet<>();

    public Payments(PaymentType paymentType, String number, String accountingNumber) {
        this.paymentType = paymentType;
        this.number = number;
        this.accountingNumber = String.valueOf(accountingNumber);
        validCards.add("4111111111111111");
        validCards.add("5555555555554444");
        validCards.add("378282246310005");
        validCards.add("6011111111111117");
        validACHeChecks.add("856667");
    }
    public String processPayment(PaymentType paymentType, String number, String accountingNumber){
        if(paymentType.equals(PaymentType.CREDIT_DEBIT)){
            return validCreditCard(number);
        }
        else if(paymentType.equals(PaymentType.ACH_ECHECK)){
            return validACHeCheck(String.valueOf(accountingNumber));
        }
        else
            return "unsuccessful";
    }
    public String validCreditCard(String number){
        if(validCards.contains(number)) {
            return "successful";
        }else return "unsuccessful";
    }
    public String validACHeCheck(String number){
        if(validACHeChecks.contains(number)) {
            return "successful";
        }else return "unsuccessful";
    }

}
