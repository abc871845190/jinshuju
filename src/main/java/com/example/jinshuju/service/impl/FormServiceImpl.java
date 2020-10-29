package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.mapper.UserMapper;
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

    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public Result createForm(User user, Form form) {
        //填补数据
        form.setFormCreateTime(new Timestamp(System.currentTimeMillis()));
        form.setFormUpdateTime(new Timestamp(System.currentTimeMillis()));
        form.setFormOpen(0);
        form.setFormViewCount(0);
        form.setFormResultViewCount(0);
        //插入form，获取id
        //log.info(form.toString());
        return doCreateForm(user, form);
    }

    private Result doCreateForm(User user, Form form) {
        if (formMapper.insertForm(form)) {
            //插入成功，原form实例添加id，获取id
            int formId = form.getFormId();
            //获取用户id
            int userId = user.getUserId();
            //根据id更新用户表单关系表
            if (formMapper.saveFormAndUser(userId, formId)) {
                //更新form的类别表
                //log.info(form.toString());
                //判断form type属性是否为空
                if (!TextUtils.isEmpty(form.getFormType())) {
                    //插入
                    if (formMapper.saveFormType(form)) {
                        return insertTemplateList(form);
                    } else {
                        deleteForm(formId);
                        return ResultUtils.fail("插入类别表失败");
                    }
                } else {
                    deleteForm(formId);
                    return ResultUtils.fail("表单类别为空");
                }
            } else {
                deleteForm(formId);
                return ResultUtils.fail("插入表单用户关系表失败");
            }
        } else {
            return ResultUtils.fail("插入表单失败");
        }
    }

    private Result insertTemplateList(Form form) {
        //批量插入表单组件关系表
        List<Template> templateList = form.getTemplateList();
        if (templateList.isEmpty()) {
            //0组件表单
            return ResultUtils.fail("插入表单成功");
        } else {
            //插入
            return formMapper.insertTemplate(form) > 0 ? ResultUtils.success("插入表单成功！") : ResultUtils.fail("插入组件失败");
        }
    }

    @Override
    public Result getForms(User user) {
        //获取用户id
        int userId = user.getUserId();
        if(userMapper.checkUserExist(userId)){
            //根据用户id获得用户所有的表单list
            List<Form> formList = formMapper.getFormsByUserId(userId);
            return ResultUtils.success("成功", formList);
        }
        return ResultUtils.fail("用户id不存在");
    }

    @Override
    public Result getForm(int formId) {
        if (formId == 0) {
            return ResultUtils.fail("id不能为0");
        }
        //判断id是否存在
        if (formMapper.checkFormById(formId)) {
            //根据表单id 拿单个表单信息
            Form form = formMapper.getFormByFormId(formId);
            if (form == null) {
                return ResultUtils.fail("提取表单失败");
            }
            return ResultUtils.success("成功", form);
        }
        return ResultUtils.fail("表单id不存在");
    }

    @Override
    public Result copyForm(User user, int formId) {
        //判0
        if (formId == 0) {
            return ResultUtils.fail("表单id为0");
        }
        //获取表单id的所有表单信息
        Form form = formMapper.getFormByFormId(formId);
        if (form != null) {
            //更新复制表单数据
            form.setFormCreateTime(new Timestamp(System.currentTimeMillis()));
            form.setFormUpdateTime(new Timestamp(System.currentTimeMillis()));
            form.setFormViewCount(0);
            form.setFormResultViewCount(0);
            //插入新表单
            return doCreateForm(user, form);
        }
        return ResultUtils.fail("该id还没创建呢");
    }

    @Override
    public Result deleteForm(int formId) {
        //判断id是否存在
        if (formMapper.checkFormById(formId)) {
            boolean flag = formMapper.deleteFormById(formId);
            log.info("delete form boolean is   ==>   " + flag);
            return flag == true ? ResultUtils.success("删除表单成功") : ResultUtils.fail("删除表单失败");
        }
        return ResultUtils.fail("表单id不存在");
    }

    @Override
    public Result updateFormName(int formId, String formName) {
        //判断为空
        if (TextUtils.isEmpty(formName)) {
            return ResultUtils.fail("表单名为空");
        }
        if (formId == 0) {
            return ResultUtils.fail("表单id不能为0");
        }
        if (checkFormId(formId)) {
            Boolean flag = formMapper.updateFormNameById(formId, formName);
            return flag == true ? ResultUtils.success("修改表单名成功") : ResultUtils.fail("表单名重复");
        }
        return ResultUtils.fail("表单id不存在");
    }

    @Override
    public Result updateFormTag(int formId, String formTag) {
        //判断为空
        if (TextUtils.isEmpty(formTag)) {
            return ResultUtils.fail("表单名为空");
        }
        if (formId == 0) {
            return ResultUtils.fail("表单id不能为0");
        }
        if (checkFormId(formId)) {
            Boolean flag = formMapper.updateFormTagById(formId, formTag);
            return flag == true ? ResultUtils.success("修改表单tag成功") : ResultUtils.fail("表单tag重复");
        }
        return ResultUtils.fail("表单id不存在");
    }

    @Override
    public Boolean checkFormId(int formId) {
        return formMapper.checkFormById(formId);
    }

    @Override
    public Result updateForm(Form form) {
        //必填的数据有 id,name = title,desc,updatetime,formimg,template
        //填补数据
        form.setFormUpdateTime(new Timestamp(System.currentTimeMillis()));
        //如果表单数据一模一样，照样运行一次插入更新操作，不会中途停止。
        //先更新form表字段所更改的内容
        formMapper.updateFormById(form);
        //删除与form绑定的组件
        int formId = form.getFormId();
        formMapper.deleteTemplateList(formId);
        //插入新的组件list
        return insertTemplateList(form);
    }

    @Override
    public void updateFormView(int formId) {
        //TODO:判断表单是否公开
        //TODO:+1浏览量
    }

    @Override
    public Result updateFormOpen(int formId) {
        //判断id是否存在
        if (formMapper.checkFormById(formId)) {
            //数据库拿open数字
            int flag = formMapper.getFormOpenByid(formId);
            if (flag == 0) {
                formMapper.updateFormOpenById(formId, flag + 1);
                return ResultUtils.success("该表单已公开填写");
            } else {
                formMapper.updateFormOpenById(formId, flag - 1);
                return ResultUtils.success("该表单已关闭公开填写");
            }
        }
        return ResultUtils.fail("表单id不存在");
    }
}
