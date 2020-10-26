package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/FormController")
@Slf4j
public class FormController {

    @Autowired(required = false)
    FormService formService;

    @Autowired(required = false)
    UserService userService;

    @PostMapping("/form")
    public Result createForm(@RequestBody Form form, HttpServletRequest request, HttpServletResponse response){
        //log.info(form.toString());
        User user = userService.checkUserLogin(request,response);
        if (user!=null){
            return formService.createForm(user,form);
        }
        return ResultUtils.fail("还没登陆呢插什么表单");
    }

}
