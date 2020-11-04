package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Data;
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
    public Result insertData(@RequestBody Data data) {
        return dataService.insertData(data);
    }

    @ApiOperation(value = "查看表单数据", response = Result.class)
    @GetMapping("/getAllData/{formId}")
    public Result getAllData(@PathVariable int formId) {
        return dataService.getAllData(formId);
    }

    @ApiOperation(value = "查看填写所有人数", response = Result.class)
    @GetMapping("/getDataCount/{formId}")
    public Result getDataCount(@PathVariable int formId) {
        return dataService.getDataCount(formId);
    }

    @ApiOperation(value = "查看当天填写人数", response = Result.class)
    @GetMapping("/getDataCountDaily/{formId}")
    public Result getDataCountDaily(@PathVariable int formId) {
        return dataService.getDataCountDaily(formId);
    }

    @ApiOperation(value = "清空数据", response = Result.class)
    @DeleteMapping("/deleteAllData/{formId]")
    public Result deleteAllData(@PathVariable int formId) {
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

    @PostMapping("/uploadExcel")
    public Result uploadExcel(@RequestParam("excelFile") MultipartFile excelFile,
                              @RequestParam("formId") int formId,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        return dataService.uploadExcel(excelFile, formId, userService.checkUserLogin(request, response));
    }
}
