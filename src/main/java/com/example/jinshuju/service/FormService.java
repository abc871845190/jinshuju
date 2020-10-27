package com.example.jinshuju.service;

import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.utils.ResultUtils.Result;

public interface FormService {
    /**
     * 创建表单
     *
     * @param form
     * @return
     */
    Result createForm(User user, Form form);

    /**
     * 查找用户的表单
     *
     * @param user
     * @return
     */
    Result getForms(User user);

    /**
     * 查找单个表单信息
     *
     * @param formId
     * @return
     */
    Result getForm(int formId);
}
