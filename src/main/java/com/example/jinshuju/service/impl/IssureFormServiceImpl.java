package com.example.jinshuju.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.mapper.IssureFormMapper;
import com.example.jinshuju.pojo.*;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.IssureFormService;
import com.example.jinshuju.utils.Constants;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.example.jinshuju.utils.TextUtils;
import com.example.jinshuju.utils.UUIDUtils;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class IssureFormServiceImpl implements IssureFormService {

    @Autowired(required = false)
    FormMapper formMapper;

    @Autowired(required = false)
    IssureFormMapper issureFormMapper;

    @Autowired(required = false)
    FormService formService;

    @Override
    public Result updateFormIssure(String formId, int userId, String formIssureTag, String formIssureDesc, String formIssureName) {
        //判空
        if (formMapper.checkFormById(formId) == null) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        if (TextUtils.isEmpty(formIssureName)) {
            return ResultUtils.fail("模板名为空");
        }
        if (TextUtils.isEmpty(formIssureTag)) {
            return ResultUtils.fail("模板标签为空");
        }

        //获取formId 原表单的所有信息
        Form form = formMapper.getFormByFormId(formId);
        //创建一个issureForm对象
        IssureForm issureForm = new IssureForm();
        //复制相应信息
        issureForm.setIssureFormId(UUIDUtils.getRandomId(16));
        issureForm.setIssureFormCreateTime(new Timestamp(new Date().getTime()));
        issureForm.setIssureFormDesc(formIssureDesc);
        issureForm.setIssureFormName(formIssureName);
        issureForm.setIssureFormTag(formIssureTag);
        issureForm.setIssureFormViewCount(0);
        issureForm.setIssureFormLikeCount(0);
        issureForm.setIssureFormUseCount(0);
        issureForm.setIssureFormImg(form.getFormImg());
        issureForm.setTemplateList(form.getTemplateList());

        //插入表模板
        if (issureFormMapper.insertFormIssure(issureForm, userId)) {
            //插入表模板组件
            if (issureForm.getTemplateList() != null && issureForm.getTemplateList().size() != 0) {
                //-------------------------------------------------------------------------------------------
                //有组件 遍历
                for (Template t : issureForm.getTemplateList()) {
                    t.setFormTemplateId(0);
                    int templateId = t.getTemplateId();
                    if (templateId == 5 || templateId == 6) {
                        //图片类型
                        //将templateImgUrl里面所有文件复制并替换
                        //获取templateImgUrl
                        String templateImgUrl = t.getTemplateImgUrl();
                        String newTemplateImgUrl = null;
                        if (!TextUtils.isEmpty(templateImgUrl)) {
                            List<DataBean> dataBeanList = JSON.parseArray(templateImgUrl, DataBean.class);
                            if (dataBeanList != null && dataBeanList.size() != 0) {
                                for (DataBean db : dataBeanList) {
                                    String imgUrl = db.getValue();
                                    log.info("updateFormIssure  ==>  oldImgUrl  ==>  " + imgUrl);
                                    if (!TextUtils.isEmpty(imgUrl)) {
                                        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                                        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                                        String newFileName = UUIDUtils.getUUID().replace("-", "") + "." + fileType;
                                        File oldFile = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + fileName);
                                        File newFile = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + newFileName);
                                        try {
                                            FileCopyUtils.copy(oldFile, newFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String newImgUrl = Constants.Url.host1 + "/img/TemplateImg/" + newFileName;
                                        log.info("updateFormIssure  ==>  newImgUrl  ==>  " + newImgUrl);
                                        //设置到新的value里面
                                        db.setValue(newImgUrl);
                                    }
                                }
                            }
                            //序列化
                            newTemplateImgUrl = JSON.toJSONString(dataBeanList);
                        }
                        t.setTemplateImgUrl(newTemplateImgUrl);
                        log.info("updateFormIssure  ==>  templateImgUrl  ==>  " + t.getTemplateImgUrl());
                    }
                }
                //---------------------------------------------------------------------------------------------
                if (issureFormMapper.insertFormIssureTemplate(issureForm)) {
                    return ResultUtils.success("表单发布模板成功");
                } else {
                    issureFormMapper.deleteIssureForm(issureForm.getIssureFormId());
                    return ResultUtils.fail("表单发布失败");
                }
            } else {
                //没组件直接成功
                return ResultUtils.success("表单发布模板成功");
            }
        }
        return ResultUtils.fail("发布失败");
    }

    @Override
    public Result getIssureForms(String keyWord, String formTag, int pageInt) {
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
        List<IssureForm> issureFormList = issureFormMapper.getIssureFormListByTagAndPage(keyWord, formTagArray, offset, rows);
        return ResultUtils.success("成功", issureFormList);
    }

    @Override
    public Result getIssurePageCount(String keyWord, String formTag) {
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
        int allCount = issureFormMapper.getFormsCountByTag(keyWord, formTagArray);
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), (allCount + rows - 1) / rows);
    }

    @Override
    public Result getIssureForm(String issureFormId) {
        //判空
        if (issureFormMapper.checkIssureForm(issureFormId) != null) {
            return ResultUtils.success("成功", issureFormMapper.getIssureFormById(issureFormId));
        }
        return ResultUtils.fail("模板id为空");
    }

    @Override
    public Result copyIssureForm(String issureFormId, User user) throws IOException, WriterException {
        //判空
        if (issureFormMapper.checkIssureForm(issureFormId) != null) {
            //获取单个模板表单
            IssureForm issureForm = issureFormMapper.getIssureFormById(issureFormId);
            //将模板转为新的form
            Form form = new Form();
            String formId = UUIDUtils.getRandomId(16);
            form.setFormId(formId);
            form.setFormCreateTime(new Timestamp(new Date().getTime()));
            form.setFormUpdateTime(new Timestamp(new Date().getTime()));
            form.setFormViewCount(0);
            form.setFormName(issureForm.getIssureFormName());
            form.setFormTitle(issureForm.getIssureFormName());
            form.setFormIsFavour(0);
            form.setFormCut(0);
            form.setFormOpen(0);
            form.setFormImg("null");
            form.setFormCutTime(new Timestamp(new Date().getTime()));
            form.setTemplateList(issureForm.getTemplateList());
            if (form.getTemplateList().size() != 0 && form.getTemplateList() != null) {
                //有组件 遍历
                for (Template t : form.getTemplateList()) {
                    t.setFormTemplateId(0);
                    int templateId = t.getTemplateId();
                    if (templateId == 5 || templateId == 6) {
                        //图片类型
                        //将templateImgUrl里面所有文件复制并替换
                        //获取templateImgUrl
                        String templateImgUrl = t.getTemplateImgUrl();
                        String newTemplateImgUrl = null;
                        if (!TextUtils.isEmpty(templateImgUrl)) {
                            List<DataBean> dataBeanList = JSON.parseArray(templateImgUrl, DataBean.class);
                            if (dataBeanList != null && dataBeanList.size() != 0) {
                                for (DataBean db : dataBeanList) {
                                    String imgUrl = db.getValue();
                                    log.info("copyForm  ==>  oldImgUrl  ==>  " + imgUrl);
                                    if (!TextUtils.isEmpty(imgUrl)) {
                                        String fileName = imgUrl.substring(imgUrl.lastIndexOf("/") + 1);
                                        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                                        String newFileName = UUIDUtils.getUUID().replace("-", "") + "." + fileType;
                                        File oldFile = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + fileName);
                                        File newFile = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + newFileName);
                                        try {
                                            FileCopyUtils.copy(oldFile, newFile);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String newImgUrl = Constants.Url.host1 + "/img/TemplateImg/" + newFileName;
                                        log.info("copyForm  ==>  newImgUrl  ==>  " + newImgUrl);
                                        //设置到新的value里面
                                        db.setValue(newImgUrl);
                                    }
                                }
                            }
                            //序列化
                            newTemplateImgUrl = JSON.toJSONString(dataBeanList);
                        }
                        t.setTemplateImgUrl(newTemplateImgUrl);
                        log.info("copyForm  ==>  templateImgUrl  ==>  " + t.getTemplateImgUrl());
                    }
                }
            }
            return formService.doCreateForm(user,form);
        }
        return ResultUtils.fail("模板id为空");
    }
}
