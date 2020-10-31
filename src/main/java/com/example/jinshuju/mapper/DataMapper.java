package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.Data;
import org.apache.ibatis.annotations.Mapper;

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
}
