package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.Data;
import com.example.jinshuju.service.DataService;
import com.example.jinshuju.utils.ResultUtils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/DataController")
@Slf4j
public class DataController {

    @Autowired(required = false)
    DataService dataService;

    @PostMapping("/Data")
    public Result insertData(@RequestBody Data data) {
        //log.info(data.toString());
        return dataService.insertData(data);
    }
}
