package com.example.server.Exceptions;

public class EmailAlreadyRegistered extends RuntimeException {
    public EmailAlreadyRegistered(String msg) {
        super(msg);
    }
}
