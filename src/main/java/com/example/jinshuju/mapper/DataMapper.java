package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DataMapper {

    /**
     * 插入数据项，影响行数大于0 返回ture 否则====
     *
     * @param data 数据bean
     * @return
     */
    Boolean insertData(Data data);

    /**
     * 插入数据项
     *
     * @param data
     * @return
     */
    Boolean insertDataDetails(Data data);

    /**
     * 根据表单id 获取该表单所有数据项
     *
     * @param formId
     * @return
     */
    List<Data> getAllDataByFormId(int formId);

    /**
     * 根据表单id 获取该id所有已填写数据量
     *
     * @param formId
     * @return
     */
    int getDataCountByFormId(int formId);

    /**
     * 根据表单id 获取该id当天已填写的数量
     *
     * @param formId
     * @return
     */
    int getDataCountDailyByFormId(int formId);

    /**
     * 根据表单id 删除该表单所有已填写的数据项，即清除数据
     *
     * @param formId
     * @return
     */
    Boolean deleteAllDataByFormId(int formId);

    /**
     * 根据数据项id（唯一值） 删除该表单下的数据项
     *
     * @param dataId
     * @return
     */
    Boolean deleteDataByDataId(int dataId);

    /**
     * 更新数据项的更新时间
     *
     * @param data
     * @return
     */
    Boolean updateDataTime(Data data);

    /**
     * 删除绑定数据项dataId 的details
     *
     * @param dataId
     * @return
     */
    Boolean deleteDataDetailsByDataId(int dataId);
}