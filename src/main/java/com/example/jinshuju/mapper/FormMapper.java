package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.Template;
import com.example.jinshuju.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface FormMapper {

    /**
     * 插入Form pojo类的基本信息到form表里面,并且返回该数据的id
     *
     * @return
     */
    Boolean insertForm(@Param("user") User user, @Param("form") Form form);

    /**
     * 根据form pojo的类别更新类别关系表
     *
     * @param form
     * @return
     */
    Boolean saveFormType(Form form);

    /**
     * 根据id 删除表单，和关联表单的所有表数据()
     *
     * @param formId
     * @return
     */
    Boolean deleteFormById(String formId);

    /**
     * 插入组件关系，没有自带id
     *
     * @param templateList
     * @return
     */
    Boolean insertNewTemplate(@Param("templateList") List<Template> templateList, @Param("formId") String formId);

    /**
     * 插入自带id的组件list
     *
     * @return
     */
    Boolean insertOldTemplate(@Param("templateList") List<Template> templateList, @Param("formId") String formId);

    /**
     * 根据用户id获取用户所有表单信息，按表单创建时间(默认)
     *
     * @param userId
     * @return
     */
    List<Form> getFormsByUserIdAndCreateTime(int userId);

    /**
     * 根据用户id获取用户所有表单信息，按数据提交时间排序
     *
     * @param userId
     * @return
     */
    List<Form> getFormsByUserIdAndDataAddTime(int userId);

    /**
     * 根据表单id查看表单信息
     *
     * @param formId
     * @return
     */
    Form getFormByFormId(String formId);

    /**
     * 根据表单id 获取表单字段list
     *
     * @param formId
     * @return
     */
    List<Template> getTemplatesByFormId(String formId);

    /**
     * 根据表单id修改表单名
     *
     * @param formId
     * @param formName
     * @return
     */
    Boolean updateFormNameById(@Param("formId") String formId, @Param("formName") String formName, @Param("time") Timestamp time);

    /**
     * 检查表单id是否存在
     *
     * @param formId
     * @return
     */
    Integer checkFormById(String formId);

    /**
     * 根据表单id修改表单Tag
     *
     * @param formId
     * @param formTag
     * @return
     */
    Boolean updateFormTagById(@Param("formId") String formId, @Param("formTag") String formTag);

    /**
     * 根据表单id获取发布标识
     *
     * @param formId
     * @return
     */
    int getFormOpenById(String formId);

    /**
     * 设置表单是否开启填写
     *
     * @param formId
     * @param formOpen
     * @return
     */
    Boolean updateFormOpenById(@Param("formId") String formId, @Param("formOpen") int formOpen, @Param("time") Timestamp time);

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
    Boolean deleteTemplateList(String formId);

//    /**
//     * 根据 tag和page 查询分页表单（都设置已公开）
//     *
//     * @param keyWord      表单名关键词
//     * @param formTagArray
//     * @param offset
//     * @param rows
//     * @return
//     */
//    List<Form> getFormsByTagAndPage(@Param("keyWord") String keyWord, @Param("formTagArray") String[] formTagArray, @Param("offset") int offset, @Param("rows") int rows);
//
//    /**
//     * 根据 tag 查询分页总数目（都设置已公开的）
//     *
//     * @param keyWord      表单名关键词
//     * @param formTagArray
//     * @return
//     */
//    int getFormsCountByTag(@Param("keyWord") String keyWord, @Param("formTagArray") String[] formTagArray);

    /**
     * 根据用户id 获取该id所填写过别人的表单，---排除自己的表单---
     *
     * @param userId
     * @return
     */
    List<Form> getFilledFormsByUserId(int userId);

    /**
     * 获取表单收藏标识
     *
     * @param formId
     * @return
     */
    int getFormFavourById(String formId);

    /**
     * 更新表单收藏
     *
     * @param formId
     * @param formFavour
     * @return
     */
    Boolean updateFormFavourById(@Param("formId") String formId, @Param("formFavour") int formFavour, @Param("time") Timestamp time);

    /**
     * 通过formId 获取表单公开url
     *
     * @param formId
     * @return
     */
    String getFormUrlById(String formId);

    /**
     * 通过formId 获取表单二维码地址
     *
     * @param formId
     * @return
     */
    String getFormQRCodeById(String formId);

    /**
     * 通过userid 获取所有收藏的表单
     *
     * @param userId
     * @return
     */
    List<Form> getFavourFormsByUserId(int userId);

    /**
     * 获取表单发布标识
     *
     * @param formId
     * @return
     */
    int getFormIssureById(String formId);

    /**
     * 通过表单id更新发布标识
     *
     * @param formId
     * @param formIssure
     * @return
     */
    Boolean updateFormIssureById(@Param("formId") String formId, @Param("formIssure") int formIssure, @Param("formIssureTag") String formIssureTag, @Param("time") Timestamp time);

    /**
     * 删除整个组件
     *
     * @param oldFormTemplateId 与表单绑定的formid
     * @return
     */
    boolean deleteTemplateById(int oldFormTemplateId);


    /**
     * 根据表单绑定组件id获取该组件的类型
     *
     * @param formTemplateId
     * @return
     */
    int getTemplateTypeByFormTemplateId(int formTemplateId);

    /**
     * 根据表单绑定组件id获取相应组件的信息
     *
     * @param formTemplateId
     * @return
     */
    Template getTemplateByFormTemplateId(int formTemplateId);

    /**
     * 更新表单绑定组件信息
     *
     * @param template
     * @return
     */
    boolean updateTemplate(Template template);

    /**
     * 获取表单绑定组件id相应的templateImgUrl
     *
     * @param formTemplateId
     * @return
     */
    String getTemplateImgUrlByFormTemplateId(int formTemplateId);

    /**
     * 判断该表单是不是属于这个用户
     *
     * @param userId
     * @param formId
     * @return
     */
    Integer judgeForm(@Param("userId") int userId, @Param("formId") String formId);

    /**
     * 获取表单用户专属标签tag
     *
     * @param formId
     * @return
     */
    String getFormTagsByFormId(String formId);

    /**
     * 获取表单截止标识
     *
     * @param formId
     * @return
     */
    int getFormCutByFormId(String formId);

    /**
     * 表单开启或关闭截止
     *
     * @param formId
     * @param flag
     * @param timestamp
     * @return
     */
    boolean updateFormCutByFormId(@Param("formId") String formId, @Param("flag") int flag, @Param("time") Timestamp timestamp);

    /**
     * 更新截止时间
     *
     * @param formId
     * @param timestamp
     * @return
     */
    boolean updateFormCutTime(@Param("formId") String formId, @Param("date") Timestamp timestamp);

    /**
     * 判断这个时间是否过期
     *
     * @param formId
     * @return
     */
    boolean judgeFormCutTime(String formId);

    /**
     * 关闭截止和开启填写功能
     *
     * @param formId
     * @return
     */
    boolean updateFormCutAndOpen(String formId);
}
