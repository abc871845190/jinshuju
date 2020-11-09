package com.example.jinshuju.service;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.utils.ResultUtils.Result;

import javax.servlet.http.HttpServletResponse;

public interface FormService {
    /**
     * 创建表单
     *
     * @param form
     * @return
     */
    Result createForm(User user, Form form);

    /**
     * 查找用户的表单,按两种排序方式
     *
     * @param user
     * @return
     */
    Result getForms(User user, int flag);

    /**
     * 查找单个表单信息
     *
     * @param formId
     * @return
     */
    Result getForm(int formId);

    /**
     * 复制同一个表单信息
     *
     * @param user
     * @param formId
     * @return
     */
    Result copyForm(User user, int formId);

    /**
     * 删除表单
     *
     * @param formId
     * @return
     */
    Result deleteForm(int formId);

    /**
     * 修改表单名
     *
     * @param formId
     * @param formName
     * @return
     */
    Result updateFormName(int formId, String formName);

    /**
     * 修改表单Tag
     *
     * @param formId
     * @param formTag
     * @return
     */
    Result updateFormTag(int formId, String formTag);

    /**
     * 检查id是否存在
     *
     * @param formId
     * @return
     */
    Boolean checkFormId(int formId);

    /**
     * 修改表单
     *
     * @param form
     * @return
     */
    Result updateForm(Form form);

    /**
     * 某id的表单浏览量+1
     *
     * @param formId
     */
    void updateFormView(int formId);

    /**
     * 设置表单是否开启填写
     * 默认为0，0为未公开
     * 1为公开填写
     *
     * @param formId
     * @return
     */
    Result updateFormOpen(int formId);

    /**
     * 获取该tag下的第n个分页
     *
     * @param keyWord
     * @param formTag
     * @param pageInt
     * @return
     */
    Result getFormsPage(String keyWord, String formTag, int pageInt);

    /**
     * 获取该tag的总分页数
     *
     * @param formTag
     * @return
     */
    Result getPageCount(String keyWord, String formTag);

    /**
     * 查看我为别人填写过的表单
     *
     * @return
     */
    Result getFilledForms(User user);

    Result getOpenFormUrl(int formId);
    void createQRCode(int formId, HttpServletResponse response);
}
