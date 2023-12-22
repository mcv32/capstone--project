package com.example.server.Exceptions;

public class EmailDoesntExist extends RuntimeException {
    public EmailDoesntExist(String msg){
        super(msg);
    }

}
