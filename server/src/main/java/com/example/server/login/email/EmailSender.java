package com.example.server.login.email;

public interface EmailSender {
    void send(String to, String email);
}
