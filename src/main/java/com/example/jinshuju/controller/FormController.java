package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(value = "表单Api接口")
public class FormController {

    @Autowired(required = false)
    FormService formService;

    @Autowired(required = false)
    UserService userService;

    @ApiOperation(value = "创建表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
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
