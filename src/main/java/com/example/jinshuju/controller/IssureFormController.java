package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.IssureFormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/IssureFormController")
@Slf4j
@Api(value = "模板表单Api接口")
public class IssureFormController {
    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    IssureFormService issureFormService;

    @ApiOperation(value = "将表单发布为模板", response = Result.class)
    @PatchMapping("/updateFormIssure")
    public Result updateFormIssure(@RequestParam("formId") String formId,
                                   @RequestParam(name = "formIssureTag", defaultValue = "") String formIssureTag,
                                   @RequestParam(name = "formIssureDesc", defaultValue = "") String formIssureDesc,
                                   @RequestParam("formIssureName") String formIssureName,
                                   HttpServletRequest request,
                                   HttpServletResponse response) {
        User user = userService.checkUserLogin(request, response);
        if (user == null) {
            return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
        }
        return issureFormService.updateFormIssure(formId, user.getUserId(), formIssureTag, formIssureDesc, formIssureName);
    }


    @ApiOperation(value = "获取所有已公开模版该tag下的第n个分页以及搜寻的关键字", response = Result.class)
    @GetMapping("/getIssureForms")
    public Result getIssureForms(@RequestParam(name = "keyWord", required = false, defaultValue = "") String keyWord,
                                 @RequestParam(name = "formTag", required = false, defaultValue = "") String formTag,
                                 @RequestParam(name = "page", required = false, defaultValue = "1") int pageInt) {
        //log.info(keyWord + "   " + formTag + "   " + pageInt);
        return issureFormService.getIssureForms(keyWord, formTag, pageInt);
    }

    @ApiOperation(value = "获取所有已公开模版该tag下的总分页数以及关键字", response = Result.class)
    @GetMapping("/getIssurePageCount")
    public Result getIssurePageCount(@RequestParam(name = "keyWord", required = false, defaultValue = "") String keyWord,
                                     @RequestParam(name = "formTag", required = false, defaultValue = "") String formTag) {
        //log.info(keyWord + "   " + formTag);
        return issureFormService.getIssurePageCount(keyWord, formTag);
    }

    @ApiOperation(value = "获取单个模板表单信息", response = Result.class)
    @GetMapping("/getIssureForm/{issureFormId}")
    public Result getIssureForm(@PathVariable("issureFormId") String issureFormId) {
        return issureFormService.getIssureForm(issureFormId);
    }
    @ApiOperation(value = "选取模板为自己的表单，要登录，选用量加一", response = Result.class)
    @GetMapping("/copyIssureForm/{issureFormId}")
    public Result copyIssureForm(@PathVariable("issureFormId")String issureFormId,HttpServletRequest request,HttpServletResponse response) throws IOException, WriterException {
        User user = userService.checkUserLogin(request, response);
        if (user == null) {
            return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
        }
        return issureFormService.copyIssureForm(issureFormId,user);
    }
}
