package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.Form;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 根据id 删除表单，和关联表单的所有表数据
     *
     * @param formId
     * @return
     */
    Boolean deleteFormById(int formId);

    /**
     * 插入组件关系
     *
     * @param form
     * @return
     */
    int insertTemplate(Form form);

    /**
     * 根据用户id获取用户所有表单信息
     *
     * @param userId
     * @return
     */
    List<Form> getFormsByUserId(int userId);

    /**
     * 根据表单id查看表单信息
     *
     * @param formId
     * @return
     */
    Form getFormByFormId(int formId);


}