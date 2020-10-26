package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.Template;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.example.jinshuju.utils.TextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class FormServiceImpl implements FormService {

    @Autowired(required = false)
    FormMapper formMapper;

    @Override
    public Result createForm(User user,Form form) {
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
                //判断form type属性是否为空
                if (!TextUtils.isEmpty(form.getFormType())){
                    //插入
                    if (formMapper.saveFormType(form)){
                        //批量插入表单组件关系表
                        List<Template> templateList = form.getTemplateList();
                        if (templateList.isEmpty()){
                            formMapper.deleteFormById(formId);
                            return ResultUtils.fail("组件为空");
                        }else{
                            //插入
                            return formMapper.insertTemplate(form) > 0 ? ResultUtils.success("插入表单成功！"):ResultUtils.fail("插入组件失败");
                        }
                    }else{
                        formMapper.deleteFormById(formId);
                        return ResultUtils.fail("插入类别表失败");
                    }
                }else{
                    formMapper.deleteFormById(formId);
                    return ResultUtils.fail("表单类别为空");
                }
            }else{
                formMapper.deleteFormById(formId);
                return ResultUtils.fail("插入表单用户关系表失败");
            }
        }else{
            return ResultUtils.fail("插入表单失败");
        }
    }
}
