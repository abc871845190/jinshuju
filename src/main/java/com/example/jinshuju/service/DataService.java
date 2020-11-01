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

    /**
     * 查看该表单所有数据
     *
     * @param formId
     * @return
     */
    Result getAllData(int formId);

    /**
     * 获取表单所有已填数据量
     *
     * @param formId
     * @return
     */
    Result getDataCount(int formId);

    /**
     * 获取当天表单填写人数
     *
     * @param formId
     * @return
     */
    Result getDataCountDaily(int formId);

    /**
     * 删除表单所有填写数据。
     *
     * @param formId
     * @return
     */
    Result deleteAllData(int formId);

    /**
     * 删除单项数据项
     *
     * @param dataId
     * @return
     */
    Result deleteData(int dataId);

    /**
     * 编辑单个数据项
     *
     * @param data
     * @return
     */
    Result updateData(Data data);
}
