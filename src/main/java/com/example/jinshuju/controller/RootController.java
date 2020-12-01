package com.example.jinshuju.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 静态控制类
 */
@Controller
@Slf4j
public class RootController {
    @RequestMapping("/")
    public String returnMsg(){
        log.info("to index");
        return "index";
    }
}