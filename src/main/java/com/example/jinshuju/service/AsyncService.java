package com.example.jinshuju.service;

public interface AsyncService {
    void sendEmail(String subject,String content,String[] toWho);
}
