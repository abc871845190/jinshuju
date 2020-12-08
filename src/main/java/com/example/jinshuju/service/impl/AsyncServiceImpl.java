package com.example.jinshuju.service.impl;

import com.example.jinshuju.service.AsyncService;
import com.example.jinshuju.service.DataService;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.MailService;
import com.example.jinshuju.utils.ResultUtils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Autowired(required = false)
    MailService mailService;

    @Autowired(required = false)
    FormService formService;

    @Autowired(required = false)
    DataService dataService;

    @Override
    @Async
    public void sendEmail(String subject, String content, String[] toWho) {
        mailService.sendHtmlMail(subject, content, toWho);
    }

    @Override
    @Async
    public Result deleteImg(String fileUrl) {
        return formService.deleteImg(fileUrl);
    }

    @Override
    @Async
    public Result testData() {
        return dataService.testData();
    }
}
