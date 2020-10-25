package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/FormController")
public class FormController {


    public Result createForm(@RequestBody Form form){
        return ResultUtils.success();
    }

}
