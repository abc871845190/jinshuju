package com.example.jinshuju.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转控制类
 */
@Controller
@Slf4j
public class RootController {
    @RequestMapping("/")
    public String returnMsg(){
        log.info("11111");
        return "index";
    }


}
