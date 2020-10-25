package com.example.jinshuju.controller;

import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转控制类
 */
@Controller
@RequestMapping("/RootController")
public class RootController {
    @RequestMapping("/returnMsg/{msg}")
    public Result returnMsg(@PathVariable("msg")String msg){
        return ResultUtils.fail(msg);
    }
}
