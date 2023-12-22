package com.example.server.Exceptions;

public class InvalidEmail extends RuntimeException {
    public InvalidEmail(String msg) {
        super(msg);
    }
}
