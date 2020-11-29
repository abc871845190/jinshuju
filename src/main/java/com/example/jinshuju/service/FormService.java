package com.example.jinshuju.service;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.google.zxing.WriterException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FormService {
    /**
     * 创建表单
     *
     * @param form
     * @return
     */
    Result createForm(User user, Form form) throws IOException, WriterException;

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
    Result copyForm(User user, int formId) throws IOException, WriterException;

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

    /**
     * 获取表单公开地址
     *
     * @param formId
     * @return
     */
    Result getOpenFormUrl(int formId);

    /**
     * 生成表单填写二维码
     *
     * @param formId
     * @param response
     */
    void createQRCode(int formId, HttpServletResponse response);

    /**
     * 表单收藏
     *
     * @param formId
     * @return
     */
    Result updateFormFavour(int formId);

    /**
     * 获取用户所有收藏表单
     *
     * @param user
     * @return
     */
    Result getFavourFroms(User user);

    /**
     * 获取表单所有组件字段信息
     *
     * @param formId
     * @return
     */
    Result getTemplates(int formId);

    /**
     * 表单公布为模版和取消公布为模版
     *
     * @param formId
     * @return
     */
    Result updateFormIssure(int formId);

    /**
     * 获取发布表单信息
     *
     * @param formId
     * @return
     */
    Result getOpenForm(int formId);

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    Result uploadImg(MultipartFile file);

    /**
     * 删除指定路径的图片
     *
     * @param fileUrl
     * @return
     */
    Result deleteImg(String fileUrl);


    Result updateForm1(Form form);

    /**
     * 删除组件content内容以及对应数据
     *
     * @param formTemplateId
     * @param mapItem
     * @return
     */
    Result deleteFormContentAndData(int formTemplateId, String targetStr);
}
