package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.mapper.UserMapper;
import com.example.jinshuju.pojo.Form;
import com.example.jinshuju.pojo.Template;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.QRCodeUtils;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.example.jinshuju.utils.TextUtils;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class FormServiceImpl implements FormService {

    @Autowired(required = false)
    FormMapper formMapper;

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    UserService userService;

    @Override
    public Result createForm(User user, Form form) throws IOException, WriterException {
        //填补数据
        form.setFormName("未命名");
        form.setFormTitle("未命名标题");
        form.setFormDesc("描述");
        form.setFormImg("null");
        form.setFormCreateTime(new Timestamp(System.currentTimeMillis()));
        form.setFormUpdateTime(new Timestamp(System.currentTimeMillis()));
        form.setFormOpen(0);
        form.setFormViewCount(0);
        form.setFormIsFavour(0);
        form.setFormIsIssure(0);
        //插入form，获取id
        //log.info(form.toString());
        return doCreateForm(user, form);
    }

    private Result doCreateForm(User user, Form form) throws IOException, WriterException {
        if (formMapper.insertForm(user, form)) {
            //插入成功，原form实例添加id，获取id
            int formId = form.getFormId();
            //生成相应公开访问填写的url 以及二维码
            String formUrl = "/f/" + String.valueOf(formId);
            form.setFormUrl(formUrl);
            String QRCodePath = QRCodeUtils.createImage(formUrl);
//            log.info(QRCodePath);
            form.setFormQRCode(QRCodePath);
            //更新表单url和二维码数据
            formMapper.updateFormById(form);
            //更新form的类别表
            //判断form type属性是否为空
            if (!TextUtils.isEmpty(form.getFormType())) {
                //插入
                if (formMapper.saveFormType(form)) {
                    return insertTemplateList(form, 0);
                } else {
                    deleteForm(formId);
                    return ResultUtils.fail("插入类别表失败");
                }
            } else {
                deleteForm(formId);
                return ResultUtils.fail("表单类别为空");
            }
        } else {
            return ResultUtils.fail("插入表单失败");
        }
    }

    private Result insertTemplateList(Form form, int flag) {
        //批量插入表单组件关系表
        List<Template> templateList = form.getTemplateList();
        if (templateList == null || templateList.size() == 0) {
            //表单没有组件-不插入组件
            if (flag == 0) {
                return ResultUtils.success("插入表单成功！", form);
            } else {
                return ResultUtils.success("插入表单成功！");
            }
        } else {
            //插入组件
            if (flag == 0) {
                return formMapper.insertTemplate(form) > 0 ? ResultUtils.success("插入表单成功！", form) : ResultUtils.fail("插入组件失败");
            } else {
                return formMapper.insertTemplate(form) > 0 ? ResultUtils.success("插入表单成功！") : ResultUtils.fail("插入组件失败");
            }
        }
    }

    @Override
    public Result getForms(User user, int flag) {
        //获取用户id
        int userId = user.getUserId();
        if (userMapper.checkUserExist(userId)) {
            List<Form> formList = null;
            if (flag == 0) {
                //根据用户id获得用户所有的表单list,按表单创建时间顺序
                formList = formMapper.getFormsByUserIdAndCreateTime(userId);
            } else {
                //按数据提交时间顺序排序
                formList = formMapper.getFormsByUserIdAndDataAddTime(userId);
            }
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
            //log.info(form.toString());
            return ResultUtils.success("成功", form);
        }
        return ResultUtils.fail("表单id不存在");
    }

    @Override
    public Result copyForm(User user, int formId) throws IOException, WriterException {
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
            return flag == true ? ResultUtils.success(ResultEnum.FORM_NAME_CHANGE.getCode(), ResultEnum.FORM_NAME_CHANGE.getMsg()) : ResultUtils.fail(ResultEnum.FORM_NAME_REPEAT.getCode(), ResultEnum.FORM_NAME_REPEAT.getMsg());
        }
        return ResultUtils.fail(ResultEnum.FORM_ENTRY.getCode(), ResultEnum.FORM_ENTRY.getMsg());
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
        //必填的数据有 id,name = title,desc,formimg,templatelist
        //填补数据
        form.setFormUpdateTime(new Timestamp(System.currentTimeMillis()));
        //如果表单数据一模一样，照样运行一次插入更新操作，不会中途停止。
        //先更新form表字段所更改的内容
        formMapper.updateFormById(form);
        //删除与form绑定的组件
        int formId = form.getFormId();
        formMapper.deleteTemplateList(formId);
        //插入新的组件list
        return insertTemplateList(form, 1);
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
            int flag = formMapper.getFormOpenById(formId);
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

    @Override
    public Result getFormsPage(String keyWord, String formTag, int pageInt) {
        //如果传值keyWord = null 或者 = "" 在sql判断
        //keyWord判空
        if (TextUtils.isEmpty(keyWord)) {
            keyWord = null;
        }
        //初定size为20个
        int rows = 20;
        int offset = (pageInt - 1) * rows;
        //解析formTag 如果formTag = null 或者 = ""
        String[] formTagArray;
        if (TextUtils.isEmpty(formTag)) {
            //一律处理formTagArray为null
            formTagArray = null;
        } else {
            //不为空
            formTagArray = TextUtils.splitString(formTag, ",");
        }
        List<Form> formList = formMapper.getFormsByTagAndPage(keyWord, formTagArray, offset, rows);
        return ResultUtils.success("成功", formList);
    }

    @Override
    public Result getPageCount(String keyWord, String formTag) {
        //keyWord判空
        if (TextUtils.isEmpty(keyWord)) {
            keyWord = null;
        }
        //初定size为20个
        int rows = 20;
        //解析formTag
        String[] formTagArray;
        if (TextUtils.isEmpty(formTag)) {
            //一律处理formTagArray为null
            formTagArray = null;
        } else {
            //不为空
            formTagArray = TextUtils.splitString(formTag, ",");
        }
        int allCount = formMapper.getFormsCountByTag(keyWord, formTagArray);
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), (allCount + rows - 1) / rows);
    }

    @Override
    public Result getFilledForms(User user) {
        if (user == null) {
            return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
        }
        //通过id获取我在data表所填写过的记录，不包括自己的表单
        List<Form> formList = formMapper.getFilledFormsByUserId(user.getUserId());
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), formList);
    }

    @Override
    public Result getOpenFormUrl(int formId) {
        //判断formId是否存在
        if (formMapper.checkFormById(formId)) {
            return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), formMapper.getFormUrlById(formId));
        }
        return ResultUtils.fail("表单不存在");
    }

    @Override
    public void createQRCode(int formId, HttpServletResponse response) {
        if (formMapper.checkFormById(formId)) {
            String QRcodePath = formMapper.getFormQRCodeById(formId);
            //TODO:....
        }
    }

    @Override
    public Result updateFormFavour(int formId) {
        //判断formid是否存在
        if (formMapper.checkFormById(formId)) {
            //数据库拿标识int
            int flag = formMapper.getFormFavourById(formId);
            if (flag == 0) {
                formMapper.updateFormFavourById(formId, flag + 1);
                return ResultUtils.success(
                        ResultEnum.FORM_IS_FAVOUR.getCode(),
                        ResultEnum.FORM_IS_FAVOUR.getMsg());
            } else {
                formMapper.updateFormFavourById(formId, flag - 1);
                return ResultUtils.success(
                        ResultEnum.FORM_IS_NOT_FAVOUR.getCode(),
                        ResultEnum.FORM_IS_NOT_FAVOUR.getMsg());
            }
        }
        return ResultUtils.fail("表单不存在");
    }

    @Override
    public Result getFavourFroms(User user) {
        if (user == null) {
            return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg());
        }
        List<Form> formList = formMapper.getFavourFormsByUserId(user.getUserId());
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), formList);
    }

    @Override
    public Result getTemplates(int formId) {
        //判断id
        if (formMapper.checkFormById(formId)) {
            List<Template> templateList = formMapper.getTemplatesByFormId(formId);
            return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), templateList);
        }
        return ResultUtils.fail("表单不存在");
    }

    @Override
    public Result updateFormIssure(int formId) {
        //判断formid是否存在
        if (formMapper.checkFormById(formId)) {
            //数据库拿标识int
            int flag = formMapper.getFormIssureById(formId);
            if (flag == 0) {
                formMapper.updateFormIssureById(formId, flag + 1);
                return ResultUtils.success("发布成功");
            } else {
                formMapper.updateFormIssureById(formId, flag - 1);
                return ResultUtils.success("取消发布成功");
            }
        }
        return ResultUtils.fail("表单不存在");
    }
}
