package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Data;
import com.example.jinshuju.service.DataService;
import com.example.jinshuju.utils.ResultUtils.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/DataController")
@Slf4j
@Api("数据项Api")
public class DataController {

    @Autowired(required = false)
    DataService dataService;

    @PostMapping("/Data")
    public Result insertData(@RequestBody Data data) {
        return dataService.insertData(data);
    }

    @GetMapping("/getAllData/{formId}")
    public Result getAllData(@PathVariable int formId) {
        return dataService.getAllData(formId);
    }

    @GetMapping("/getDataCount/{formId}")
    public Result getDataCount(@PathVariable int formId) {
        return dataService.getDataCount(formId);
    }

    @GetMapping("/getDataCountDaily/{formId}")
    public Result getDataCountDaily(@PathVariable int formId) {
        return dataService.getDataCountDaily(formId);
    }

    @DeleteMapping("/deleteAllData/{formId]")
    public Result deleteAllData(@PathVariable int formId) {
        return dataService.deleteAllData(formId);
    }

    @DeleteMapping("/deleteData/{dataId}")
    public Result deleteData(@PathVariable int dataId) {
        return dataService.deleteData(dataId);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Data data) {
        return dataService.updateData(data);
    }
}
