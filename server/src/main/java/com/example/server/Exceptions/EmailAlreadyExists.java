package com.example.server.Exceptions;

public class EmailAlreadyExists extends RuntimeException{

    public EmailAlreadyExists(String msg){
        super(msg);
    }
}
