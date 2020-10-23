package com.example.jinshuju.service.impl;

import com.example.jinshuju.service.AsyncService;
import com.example.jinshuju.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Autowired(required = false)
    MailService mailService;

    @Override
    @Async
    public void sendEmail(String subject, String content, String[] toWho) {
        mailService.sendHtmlMail(subject, content, toWho);
    }
}
