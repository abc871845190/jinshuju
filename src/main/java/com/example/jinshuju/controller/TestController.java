package com.example.jinshuju.controller;

import com.example.jinshuju.service.MailService;
import com.example.jinshuju.utils.TextUtils;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/TestController")
public class TestController {

    @Autowired(required = false)
    MailService mailService;

    @GetMapping("/test")
    public void test(){
        //TODO:
        log.info("12313123");
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    @ApiOperation("验证邮箱")
    @PostMapping("/checkEmail")
    public Result checkEmail() {

        //mailService.sendHtmlMail("测试邮件","测试邮件吼吼", new String[]{"871845190@qq.com"});
        log.info(String.valueOf(TextUtils.RandomCode()));
        return ResultUtils.success();
    }
}
