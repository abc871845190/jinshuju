package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.DataMapper;
import com.example.jinshuju.pojo.Data;
import com.example.jinshuju.service.DataService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
public class DataServiceImpl implements DataService {

    @Autowired(required = false)
    DataMapper dataMapper;

    @Override
    public Result insertData(Data data) {
        //补充数据

        data.setDataCreateTime(new Timestamp(System.currentTimeMillis()));
        //第一，先插入data表数据项
        if (dataMapper.insertData(data)){
            //ture    现dataid为新插入数据项id
            if (dataMapper.insertDataDetails(data)){
                //ture
                return ResultUtils.success();
            }else{
                //false
                return ResultUtils.fail();
            }
        }else{
            //false
            return ResultUtils.fail();
        }
    }
}
