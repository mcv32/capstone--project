package com.example.server.Exceptions;

public class IncorrectPassword extends RuntimeException{
    public IncorrectPassword(String msg){
        super(msg);
    }
}
