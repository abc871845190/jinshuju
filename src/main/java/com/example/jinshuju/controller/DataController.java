package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Data;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.DataService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.ResultUtils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/DataController")
@Slf4j
@Api("数据项Api")
public class DataController {

    @Autowired(required = false)
    DataService dataService;

    @Autowired(required = false)
    UserService userService;

    @ApiOperation(value = "添加表单数据", response = Result.class)
    @PostMapping("/Data")
    public Result insertData(@RequestBody Data data,HttpServletRequest request,HttpServletResponse response) {
        User user = userService.checkUserLogin(request,response);
        if (user!= null){
            //登陆了就放id
            data.setUser(user);
        }else{
            //没登陆就放0
            data.setUser(new User());
            data.getUser().setUserId(0);
        }
        return dataService.insertData(data);
    }

    @ApiOperation(value = "查看表单数据", response = Result.class)
    @GetMapping("/getAllData/{formId}")
    public Result getAllData(@PathVariable String formId) {
        return dataService.getAllData(formId);
    }

    @ApiOperation(value = "查看填写所有人数", response = Result.class)
    @GetMapping("/getDataCount/{formId}")
    public Result getDataCount(@PathVariable String formId) {
        return dataService.getDataCount(formId);
    }

    @ApiOperation(value = "查看当天填写人数", response = Result.class)
    @GetMapping("/getDataCountDaily/{formId}")
    public Result getDataCountDaily(@PathVariable String formId) {
        return dataService.getDataCountDaily(formId);
    }

    @ApiOperation(value = "清空数据", response = Result.class)
    @DeleteMapping("/deleteAllData/{formId]")
    public Result deleteAllData(@PathVariable String formId) {
        return dataService.deleteAllData(formId);
    }

    @ApiOperation(value = "删除单个数据项", response = Result.class)
    @DeleteMapping("/deleteData/{dataId}")
    public Result deleteData(@PathVariable int dataId) {
        return dataService.deleteData(dataId);
    }

    @ApiOperation(value = "修改单个数据项", response = Result.class)
    @PutMapping("/updateData")
    public Result updateData(@RequestBody Data data) {
        return dataService.updateData(data);
    }

    @ApiOperation(value = "导入excel文件 --excel头字段必须有原表单设置的所有字段，最好一对一对应--", response = Result.class)
    @PostMapping("/uploadExcel")
    public Result uploadExcel(@RequestParam("excelFile") MultipartFile excelFile,
                              @RequestParam("formId") String formId,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        return dataService.uploadExcel(excelFile, formId, userService.checkUserLogin(request, response));
    }

    @ApiOperation(value = "批量删除data    id为String类型", response = Result.class)
    @DeleteMapping("/deleteBatch")
    public Result deleteBatch(@RequestParam(name = "id",defaultValue = "") String idList){
        return dataService.deleteBatch(idList);
    }

}
