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
import org.springframework.web.bind.annotation.*;

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
    public Result createForm(@RequestBody Form form, HttpServletRequest request, HttpServletResponse response) {
        //log.info(form.toString());
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.createForm(user, form);
        }
        return ResultUtils.fail("还没登陆呢插什么表单");
    }

    @ApiOperation(value = "查找用户表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/form")
    public Result getForms(HttpServletRequest request, HttpServletResponse response) {
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.getForms(user);
        }
        return ResultUtils.fail("还没登陆呢，怎么查表单");
    }

    @ApiOperation(value = "查找用户单个表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/form/{formId}")
    public Result getForm(@PathVariable("formId") int formId) {
        return formService.getForm(formId);
    }

    @ApiOperation(value = "复制表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PostMapping("/copy/{formId}")
    public Result copyForm(@PathVariable("formId") int formId,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.copyForm(user, formId);
        }
        return ResultUtils.fail("还没登陆呢，怎么复制");
    }

    @ApiOperation(value = "删除表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @DeleteMapping("/form/{formId}")
    public Result deleteForm(@PathVariable("forId") int formId){
        return formService.deleteForm(formId);
    }

    @ApiOperation(value = "修改表单名", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PutMapping("/updateName/{formId}/{formName}")
    public Result updateFormName(@PathVariable("formName") String formName,
                                 @PathVariable("formId") int formId){
        return formService.updateFormName(formId,formName);
    }

    @ApiOperation(value = "修改表单tag", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PutMapping("/updateTag/{formId}/{formTag}")
    public Result updateFormTag(@PathVariable("formTag") String formTag,
                                @PathVariable("formId") int formId){
        return formService.updateFormTag(formId,formTag);
    }

    @ApiOperation(value = "修改表单,要附带id", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PutMapping("/updateForm")
    public Result updateForm(@RequestBody Form form){
        return formService.updateForm(form);
    }

    @ApiOperation(value = "设置表单是否开启填写", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PutMapping("/updateOpen/{formId}")
    public Result updateFormOpen(@PathVariable("formId") int formId){
        return formService.updateFormOpen(formId);
    }
}
