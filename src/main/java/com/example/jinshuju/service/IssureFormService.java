package com.example.jinshuju.service;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.google.zxing.WriterException;

import java.io.IOException;

public interface IssureFormService {

    /**
     * 将表单发布为模板
     *
     * @param formId
     * @param userId
     * @param formIssureTag
     * @param formIssureDesc
     * @param formIssureName
     * @return
     */
    Result updateFormIssure(String formId, int userId, String formIssureTag, String formIssureDesc, String formIssureName);

    /**
     * 获取该tag下的第n个分页的模板表单
     *
     * @param keyWord
     * @param formTag
     * @param pageInt
     * @return
     */
    Result getIssureForms(String keyWord, String formTag, int pageInt);

    /**
     * 获取该tag的总分页数
     *
     * @param keyWord
     * @param formTag
     * @return
     */
    Result getIssurePageCount(String keyWord, String formTag);

    /**
     * 获取单个表单模板信息
     *
     * @param issureFormId
     * @return
     */
    Result getIssureForm(String issureFormId);

    /**
     * 将模板选取为自己的表单
     *
     * @param issureFormId
     * @param user
     * @return
     */
    Result copyIssureForm(String issureFormId, User user) throws IOException, WriterException;
}
