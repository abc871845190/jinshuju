package com.example.jinshuju.service;

import com.example.jinshuju.pojo.Data;
import com.example.jinshuju.utils.ResultUtils.Result;

public interface DataService {

    /**
     * 插入数据项
     *
     * @param data
     * @return
     */
    Result insertData(Data data);
}
