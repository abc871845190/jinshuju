package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.AsyncService;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/FormController")
@Slf4j
@Api(value = "表单Api接口")
public class FormController {

    @Autowired(required = false)
    FormService formService;

    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    AsyncService asyncService;

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
    public Result getForm(@PathVariable("formId") String formId) {
        return formService.getForm(formId);
    }

    @ApiOperation(value = "复制表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PostMapping("/copy/{formId}")
    public Result copyForm(@PathVariable("formId") String formId,
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
    public Result deleteForm(@PathVariable("formId") String formId) {
        return formService.deleteForm(formId);
    }

    @ApiOperation(value = "修改表单名  ====>formTitle", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateName/{formId}/{formName}")
    public Result updateFormName(@PathVariable("formName") String formName,
                                 @PathVariable("formId") String formId) {
        return formService.updateFormName(formId, formName);
    }

    @ApiOperation(value = "修改表单用户专属tag", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateTag/{formId}/{formTag}")
    public Result updateFormTag(@PathVariable("formTag") String formTag,
                                @PathVariable("formId") String formId) {
        return formService.updateFormTag(formId, formTag);
    }

    @ApiOperation(value = "设置表单是否开启填写", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateOpen/{formId}")
    public Result updateFormOpen(@PathVariable("formId") String formId) {
        return formService.updateFormOpen(formId);
    }

    @ApiOperation(value = "获取所有已公开模版该tag下的第n个分页以及搜寻的关键字", response = Result.class)
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

    @ApiOperation(value = "获取所有已公开模版该tag下的总分页数以及关键字", response = Result.class)
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
    public Result getOpenFormUrl(@PathVariable("formId") String formId) {
        return formService.getOpenFormUrl(formId);
    }

    @GetMapping("/createQRCode/{formId}")
    public void createQRCodeByUrl(HttpServletResponse response, @PathVariable("formId") String formId) {
        formService.createQRCode(formId, response);
    }

    @ApiOperation(value = "收藏表单和取消收藏表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 20, message = "收藏成功"),
            @ApiResponse(code = 21, message = "取消收藏成功"),
            @ApiResponse(code = 1, message = "失败")
    })
    @PatchMapping("/updateFavour/{formId}")
    public Result updateFavour(@PathVariable("formId") String formId) {
        return formService.updateFormFavour(formId);
    }

    @ApiOperation(value = "获取用户所有收藏表单", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getFavourForms")
    public Result getFavourForms(HttpServletRequest request, HttpServletResponse response) {
        User user = userService.checkUserLogin(request, response);
        return formService.getFavourFroms(user);
    }

    @ApiOperation(value = "获取该表单所有组件字段信息", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/getTemplates/{formId}")
    public Result getTemplates(@PathVariable("formId") String formId) {
        return formService.getTemplates(formId);
    }

    @ApiOperation(value = "表单发布为模版和取消发布为模版", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateIssure/{formId}")
    public Result updateIssure(@PathVariable("formId") String formId) {
        return formService.updateFormIssure(formId);
    }

    @ApiOperation(value = "获取发布表单", response = Result.class)
    @GetMapping("/getOpenForm/{formId}")
    public Result getOpenForm(@PathVariable("formId") String formId) {
        return formService.getOpenForm(formId);
    }

    @ApiOperation(value = "上传单张图片    用于组件图片上传", response = Result.class)
    @PostMapping("/uploadImg")
    public Result uploadImg(@RequestParam("file") MultipartFile file) {
        return formService.uploadImg(file);
    }

    @ApiOperation(value = "删除单张图片    用于组件图片删除", response = Result.class)
    @DeleteMapping("/deleteImg")
    public Result deleteImg(@RequestParam("fileUrl") String fileUrl) {
        log.info("fileUrl   ==>   " + fileUrl);
        return asyncService.deleteImg(fileUrl);
    }

    @ApiOperation(value = "修改表单,要附带表单id", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PutMapping("/updateForm")
    public Result updateForm(@RequestBody Form form) {
        //log.info(form.toString());
        return formService.updateForm(form);
    }

    /**
     * 删除组件content内容以及对应数据  前端
     * <p>
     * 数据库：多选  ==>  [{key:"0",value:"xx"},{key:"1",value:"xxx"},{key:"2",value:"啊啊啊"}]
     * 单选  ==>  {key:"0",value:"xx"}
     *
     * @param formTemplateId 绑定表单的组件id
     * @param mapItem        需要删除的key-value 字符串
     * @return
     */
    @ApiOperation(value = "删除表单组件对应的content以及数据content", response = Result.class)
    @DeleteMapping("/deleteFormContentAndData")
    public Result deleteFormContentAndData(@RequestParam("formTemplateId") int formTemplateId, @RequestParam("optionKey") String mapItem) {
        return formService.deleteFormContentAndData(formTemplateId, mapItem);
    }

    @ApiOperation(value = "判断该表单是不是属于这个用户", response = Result.class)
    @GetMapping("/judgeForm")
    public Result judgeForm(HttpServletRequest request, HttpServletResponse response, @RequestParam("formId") String formId) {
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            return formService.judgeForm(user.getUserId(), formId);
        } else {
            return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
        }
    }

    @ApiOperation(value = "获取表单用户专属标签tag", response = Result.class)
    @GetMapping("/getFormTags")
    public Result getFormTags(String formId) {
        return formService.getFormTags(formId);
    }

    @ApiOperation(value = "判断该表单是否公开填写", response = Result.class)
    @GetMapping("/judgeFormOpen")
    public Result judgeFormOpen(@RequestParam("formId") String formId) {
        return formService.judgeFormOpen(formId);
    }

    @ApiOperation(value = "判断该表单是否截止填写，过时关闭公开填写", response = Result.class)
    @GetMapping("/judgeFormCut")
    public Result judgeFormCut(@RequestParam("formId") String formId) {
        return formService.judgeFormCut(formId);
    }

    @ApiOperation(value = "表单公开填写开启和关闭截止功能，默认两小时", response = Result.class)
    @PostMapping("/updateFormCut")
    public Result updateFormCut(@RequestParam("formId") String formId) {
        return formService.updateFormCut(formId);
    }

    @ApiOperation(value = "表单公开填写开启和关闭截止功能，默认两小时", response = Result.class)
    @PostMapping("/addFormCutTime")
    public Result addFormCutTime(@RequestParam("formId")String formId,@RequestParam("date") Date date){
        return formService.updateFormCutTime(formId,date);
    }
}