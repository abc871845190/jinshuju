package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.Form;
import org.apache.ibatis.annotations.Param;

public interface FormMapper {

    /**
     * 插入Form pojo类的基本信息到form表里面,并且返回该数据的id
     *
     * @return
     */
    Boolean insertForm(Form form);

    /**
     * 存储From和User的关系，存外键表
     *
     * @param userId
     * @param formId
     * @return
     */
    Boolean saveFormAndUser(@Param("userId") int userId, @Param("formId") int formId);

    /**
     * 根据form pojo的类别更新类别关系表
     *
     * @param form
     * @return
     */
    Boolean saveFormType(Form form);
}
