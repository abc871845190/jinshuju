package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@Slf4j
public class FormServiceImpl implements FormService {

    @Autowired(required = false)
    FormMapper formMapper;

    @Override
    public Result createForm(User user,Form form) {
        //判空
        //填补数据
        form.setFormCreateTime(new Timestamp(System.currentTimeMillis()));
        form.setFormUpdateTime(new Timestamp(System.currentTimeMillis()));
        form.setFormOpen(0);
        form.setFormViewCount(0);
        form.setFormResultViewCount(0);
        //插入form，获取id

        if (formMapper.insertForm(form)){
            //插入成功，原form实例添加id，获取id
            int formId = form.getFormId();
            //获取用户id
            int userId = user.getUserId();
            //根据id更新用户表单关系表
            if(formMapper.saveFormAndUser(userId,formId)){
                //更新form的类别表
                log.info(form.toString());
                if (formMapper.saveFormType(form)){
                    return ResultUtils.success("插入表单成功");
                }else{
                    return ResultUtils.fail("插入类别表失败");
                }
            }else{
                return ResultUtils.fail("插入表单用户关系表失败");
            }
        }else{
            return ResultUtils.fail("插入表单失败");
        }
    }
}
