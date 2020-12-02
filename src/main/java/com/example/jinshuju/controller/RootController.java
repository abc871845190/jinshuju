package com.example.jinshuju.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 静态控制类
 */
@Controller
@Slf4j
public class RootController {
    @GetMapping("/jinshuju/")
    public ModelAndView returnMsg(){
        log.info("to index");
        return new ModelAndView("index");
    }
}