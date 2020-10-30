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

    /**
     * 根据表单id修改表单名
     *
     * @param formId
     * @param formName
     * @return
     */
    Boolean updateFormNameById(@Param("formId") int formId, @Param("formName") String formName);

    /**
     * 检查表单id是否存在
     *
     * @param formId
     * @return
     */
    Boolean checkFormById(int formId);

    /**
     * 根据表单id修改表单Tag
     *
     * @param formId
     * @param formTag
     * @return
     */
    Boolean updateFormTagById(@Param("formId") int formId, @Param("formTag") String formTag);

    /**
     * 根据表单id获取公开填写信息
     *
     * @param formId
     * @return
     */
    int getFormOpenByid(int formId);

    /**
     * 设置表单是否开启填写
     *
     * @param formId
     * @param formOpen
     * @return
     */
    Boolean updateFormOpenById(int formId, int formOpen);

    /**
     * 根据表单id更新表单信息
     *
     * @param form
     * @return
     */
    Boolean updateFormById(Form form);

    /**
     * 根据表单id删除绑定的组件list
     *
     * @param formId
     * @return
     */
    Boolean deleteTemplateList(int formId);

    /**
     * 根据 tag和page 查询分页表单（都设置已公开）
     *
     * @param formTagArray 标签List
     * @param offset       从n个数据开始
     * @param rows         一次查询返回的size
     * @return
     */
    List<Form> getFormsByTagAndPage(@Param("formTagArray") String[] formTagArray, @Param("offset") int offset, @Param("rows") int rows);

    /**
     * 根据 tag 查询分页总数目（都设置已公开的）
     *
     * @return
     */
    int getFormsCountByTag(String[] formTagArray);
}
