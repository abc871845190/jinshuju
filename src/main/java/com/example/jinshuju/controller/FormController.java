package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public Result createForm(@RequestBody Form form, HttpServletRequest request, HttpServletResponse response) throws IOException, WriterException {
        //log.info(form.toString());
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.createForm(user, form);
        }
        return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
    }

    @ApiOperation(value = "查找用户表单+表单排序", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/form")
    public Result getForms(@RequestParam(name = "flag", defaultValue = "0", required = false) int flag,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.getForms(user, flag);
        }
        return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
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
                           HttpServletResponse response) throws IOException, WriterException {
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.copyForm(user, formId);
        }
        return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
    }

    @ApiOperation(value = "删除表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @DeleteMapping("/form/{formId}")
    public Result deleteForm(@PathVariable("forId") int formId) {
        return formService.deleteForm(formId);
    }

    @ApiOperation(value = "修改表单名", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateName/{formId}/{formName}")
    public Result updateFormName(@PathVariable("formName") String formName,
                                 @PathVariable("formId") int formId) {
        return formService.updateFormName(formId, formName);
    }

    @ApiOperation(value = "修改表单tag", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateTag/{formId}/{formTag}")
    public Result updateFormTag(@PathVariable("formTag") String formTag,
                                @PathVariable("formId") int formId) {
        return formService.updateFormTag(formId, formTag);
    }

    @ApiOperation(value = "修改表单,要附带表单id", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PutMapping("/updateForm")
    public Result updateForm(@RequestBody Form form) {
        return formService.updateForm(form);
    }

    @ApiOperation(value = "设置表单是否开启填写", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateOpen/{formId}")
    public Result updateFormOpen(@PathVariable("formId") int formId) {
        return formService.updateFormOpen(formId);
    }

    @ApiOperation(value = "获取所有已公开表单该tag下的第n个分页以及搜寻的关键字", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getFormsPage")
    public Result getFormsPage(@RequestParam(name = "keyWord", required = false, defaultValue = "") String keyWord,
                               @RequestParam(name = "formTag", required = false, defaultValue = "") String formTag,
                               @RequestParam(name = "page", required = false, defaultValue = "1") int pageInt) {
        //log.info(keyWord + "   " + formTag + "   " + pageInt);
        return formService.getFormsPage(keyWord, formTag, pageInt);
    }

    @ApiOperation(value = "获取所有已公开表单该tag下的总分页数以及关键字", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getPageCount")
    public Result getPageCount(@RequestParam(name = "keyWord", required = false, defaultValue = "") String keyWord,
                               @RequestParam(name = "formTag", required = false, defaultValue = "") String formTag) {
        //log.info(keyWord + "   " + formTag);
        return formService.getPageCount(keyWord, formTag);
    }

    @ApiOperation(value = "查看我为别人填写过的表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getFillesForms")
    public Result getFilledForms(HttpServletRequest request, HttpServletResponse response) {
        return formService.getFilledForms(userService.checkUserLogin(request, response));
    }

    @ApiOperation(value = "获取公开模块的url地址", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getOpenFormUrl/{formId}")
    public Result getOpenFormUrl(@PathVariable("formId") int formId){
        return formService.getOpenFormUrl(formId);
    }

    @GetMapping("/createQRCode/{formId}")
    public void createQRCodeByUrl(HttpServletResponse response,@PathVariable("formId") int formId){
        formService.createQRCode(formId, response);
    }

    @ApiOperation(value = "收藏表单和取消收藏表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateFavour/{formId}")
    public Result updateFavour(@PathVariable("formId") int formId){
        return formService.updateFormFavour(formId);
    }

    @ApiOperation(value = "获取所有用户收藏表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getFavourForms")
    public Result getFavourForms(HttpServletRequest request,HttpServletResponse response){
        User user = userService.checkUserLogin(request,response);
        return formService.getFavourFroms(user);
    }

    @ApiOperation(value = "公开模版界面填写专用url", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/{formId}")
    public Result getOpenForm(@PathVariable("formId")int formId){
        //TODO:返回公开模版填写需要的信息
        return ResultUtils.success();
    }

    @ApiOperation(value = "获取该表单所有组件字段信息", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getTemplates/{formId}")
    public Result getTemplates(@PathVariable("formId") int formId){
        return formService.getTemplates(formId);
    }
}