package com.example.server.Exceptions;

public class PaymentInfoInvalid extends RuntimeException{
    public PaymentInfoInvalid(String msg){
        super(msg);
    }
}
