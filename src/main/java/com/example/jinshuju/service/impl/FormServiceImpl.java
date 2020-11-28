package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.DataMapper;
import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.mapper.UserMapper;
import com.example.jinshuju.pojo.*;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.Constants;
import com.example.jinshuju.utils.QRCodeUtils;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.example.jinshuju.utils.TextUtils;
import com.example.jinshuju.utils.UUIDUtils;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
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

    @Autowired(required = false)
    DataMapper dataMapper;

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
                    return insertTemplateList(form, null, 0);
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

    private Result insertTemplateList(Form form, List<Data> dataList, int flag) {
        int formId = form.getFormId();
        //批量插入表单组件关系表
        List<Template> templateList = form.getTemplateList();
        //
        List<Template> haveOldTemplateList = new ArrayList<>();
        List<Template> haveNewTemplateList = new ArrayList<>();

        if (templateList == null || templateList.size() == 0) {
            //表单没有组件-不插入组件
            if (flag == 0) {
                return ResultUtils.success("插入表单成功！", form);
            } else {
                return ResultUtils.success("插入表单成功！");
            }
        } else {
            //拆分 by formTemplateId
            for (Template template : templateList) {
                if (template.getFormTemplateId() != 0) {
                    //不等于0 为旧的template
                    haveOldTemplateList.add(template);
                } else {
                    //等于0 为新的template
                    haveNewTemplateList.add(template);
                }
            }
            //插入组件
            if (flag == 0) {
                //判断旧的是否有组件
                if (haveOldTemplateList != null && haveOldTemplateList.size() != 0) {
                    //先插入旧的
                    formMapper.insertOldTemplate(haveOldTemplateList, formId);
                }
                //判断是否有新的组件  插入新的组件
                if (haveNewTemplateList == null || haveNewTemplateList.size() == 0) {

                } else {
                    formMapper.insertNewTemplate(haveNewTemplateList, formId);
                }
                //插入备份的组件list的数据
                if (dataList != null && dataList.size() != 0) {
                    for (Data d : dataList) {
                        d.setForm(new Form());
                        d.getForm().setFormId(formId);
                    }
                    dataMapper.insertDataDetailsList(dataList);
                }
                return ResultUtils.success("插入表单成功", form);
            } else {
                //判断旧的是否有组件
                if (haveOldTemplateList != null && haveOldTemplateList.size() != 0) {
                    //先插入旧的
                    formMapper.insertOldTemplate(haveOldTemplateList, formId);
                }
                //判断是否有新的组件  插入新的组件
                if (haveNewTemplateList == null || haveNewTemplateList.size() == 0) {

                } else {
                    formMapper.insertNewTemplate(haveNewTemplateList, formId);
                }
                //插入备份的组件list的数据
                if (dataList != null && dataList.size() != 0) {
                    for (Data d : dataList) {
                        d.setForm(new Form());
                        d.getForm().setFormId(formId);
                    }
                    dataMapper.insertDataDetailsList(dataList);
                }
                return ResultUtils.success("插入表单成功");
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
            form.setFormName(form.getFormName() + "-副本");
            form.setFormIsFavour(0);
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
        //获取表单id
        int formId = form.getFormId();
        //获取原来组件list
        List<Template> templateList = formMapper.getTemplatesByFormId(formId);
        //新组件list
        List<Template> newTemplateList = form.getTemplateList();
        //删除原组件与新组件不同的组件 新的不管 旧的全删，更新对应组件修改内容以及数据项内容
        updateOldTemplate(templateList, newTemplateList, formId);
        //备份原来绑定组件list的数据
        List<Data> dataList = dataMapper.getAllDataByFormId(formId);

        //删除与form绑定的组件
        formMapper.deleteTemplateList(formId);

        //插入新的组件list
        return insertTemplateList(form, dataList, 1);
    }

    /**
     * 删除原组件与新组件不同的组件 新的不管 旧的全删
     *
     * @param templateList
     * @param newTemplateList
     * @param formId
     */
    private void updateOldTemplate(List<Template> templateList, List<Template> newTemplateList, int formId) {
        //遍历
        boolean flag = false;
        for (Template oldTemplate : templateList) {
            for (Template newTemplate : newTemplateList) {
                //（1）如果有formTemplateId 循环遍历查找原来组件list是否有这个id 即判断是否为0
                if (newTemplate.getFormTemplateId() == 0) {
                    //（2）如果没有formTemplateId 即为新的组件 没有动作
                } else {
                    // 有就是先对比是否删减旧的组件
                    if (oldTemplate.getFormTemplateId() == newTemplate.getFormTemplateId()) {
                        //原组件
                        //1、拿formTemplateList所有组件content
                        //2、判断是否为空
                        if (oldTemplate.getTemplateContent() != "null" && newTemplate.getTemplateContent() != "null") {
                            log.info("--------------------原组件content类型为数组----------------------");
                            //3、拿数据库对应form的所有组件content
                            //4、对比content内容删改
                            String[] oldContentArray = TextUtils.splitJsonString(oldTemplate.getTemplateContent(), ",");
                            String[] newContentArray = TextUtils.splitJsonString(newTemplate.getTemplateContent(), ",");
                            //5、将删改内容动态修改相应dataDetails的数据项
                            //分三种情况
                            if (oldContentArray.length == newContentArray.length) {
                                //以旧的content长度为基准
                                updateDataContent(formId, oldTemplate, oldContentArray, newContentArray, 0);
                            } else if (oldContentArray.length > newContentArray.length) {
                                //2、旧的数组长度比新的多   --删减了组件content
                                //以新的content长度为基准
                                updateDataContent(formId, oldTemplate, oldContentArray, newContentArray, 1);

                                //TODO:删除已经选择过该选项的数据详细  分多选[和单选区别修改
                            } else if (oldContentArray.length < newContentArray.length) {
                                //3、旧的数组长度比新的少   --增加了组件content
                                log.info("------------------3updateDataContentByReplace-------------------");
                                updateDataContent(formId, oldTemplate, oldContentArray, newContentArray, 0);
                            }
                        }
                        //对比一样的话 即新的组件有旧的组件 设置标识
                        flag = true;
                        //跳出循环
                        break;
                    }
                }
            }
            if (!flag) {
                //删除该template以及绑定的所有数据项
                //删除该id所有数据项
                int oldFormTemplateId = oldTemplate.getFormTemplateId();
                dataMapper.deleteDataDetailsByFormTemplateId(oldFormTemplateId);
            } else {
                flag = false;
            }
        }
    }

    /**
     * 对原组件的content修改进行数据动态更新，
     *
     * @param formId
     * @param oldTemplate
     * @param oldContentArray
     * @param newContentArray
     * @param flag            0为按原组件结构进行匹配 1为按新组件结构进行匹配
     */
    private void updateDataContent(int formId, Template oldTemplate, String[] oldContentArray, String[] newContentArray, int flag) {
        //1、两个数组长度相同
        //操作上仅仅修改了原有组件content的内容 没有增删
        log.info("------------------新旧组件content元素长度相等-------------------");
        //获取该列的所有数据项
        List<Data> dataList = dataMapper.getAllDataByFormId(formId);
        //判断对比长度类型
        String[] targetContentArray;
        if (flag == 0){
            targetContentArray = oldContentArray;
        }else{
            targetContentArray = newContentArray;
        }
        //对比content是否有更新项
        for (int i = 0; i < targetContentArray.length; i++) {
            //记录该更新项的字符串信息
            String targetNewContentItem = newContentArray[i];
            //记录旧选项的字符串信息
            String targetOldContentItem = oldContentArray[i];
            //开始对比
            if (!targetOldContentItem.equals(targetNewContentItem)) {
                //匹配发现有选项被更新
                //先判断是否填写的数据
                if (dataList != null && dataList.size() != 0) {
                    //已经填写过数据
                    //开始遍历所有数据项
                    for (Data d : dataList) {
                        //遍历数据项的所有字段
                        for (DataDetails dd : d.getDataDetailsList()) {
                            //匹配相应的字段
                            if (dd.getFormTemplateId() == oldTemplate.getFormTemplateId()) {
                                //获取到了对应的字段
                                //提取里面的数据内容，解析
                                //数据形式分两种   单选没有‘[]’ 多选有‘[]’
                                String oldDataContent = dd.getDataContent();
                                //判空
                                if (!TextUtils.isEmpty(oldDataContent)) {
                                    //判断第一个字符有没有‘[’
                                    if (TextUtils.indexOfStr(oldDataContent, "[", 0)) {
                                        //多选
                                        //将数组字符串分割成数组
                                        String[] oldDataContentArray = TextUtils.splitJsonString(oldDataContent, ",");
                                        //准备设置新的字符串数组
                                        List<String> newDataContentList = new ArrayList<>();
                                        //遍历 对比
                                        for (String s : oldDataContentArray) {
                                            //匹配是否有选择该项的数据
                                            if (s.equals(targetOldContentItem)) {
                                                //替换
                                                s = targetNewContentItem;
                                            }
                                            //按原顺序添加到list里面
                                            newDataContentList.add(s);
                                        }
                                        String newDataContent = "";
                                        //循环结束，将list拼接成js数组字符串
                                        if (newDataContentList.size() != 0) {
                                            newDataContent += "[";
                                            //遍历
                                            for (int j = 0; j < newDataContentList.size(); j++) {
                                                newDataContent += "\"" + newDataContentList.get(j) + "\"";
                                                if (j != newDataContentList.size() - 1) {
                                                    newDataContent += ",";
                                                }
                                            }
                                            newDataContent += "]";
                                        }
                                        //拼接完成 替换原来的content数据
                                        dd.setDataContent(newDataContent);
                                    } else {
                                        //单选
                                        //不用截选 直接配对
                                        String newDataContent = "";
                                        if (oldDataContent.equals(targetOldContentItem)) {
                                            //替换
                                            newDataContent = targetNewContentItem;
                                        }
                                        //设置数据
                                        dd.setDataContent(newDataContent);
                                    }
                                }
                            }
                        }
                    }
                }
                //没填写过数据 - 无动作
            }
        }
        //将新的数据update到数据库里面
        dataMapper.updateDataDetailsList(dataList);
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
                return ResultUtils.success(ResultEnum.FORM_IS_OPEN.getCode(), ResultEnum.FORM_IS_OPEN.getMsg());
            } else {
                formMapper.updateFormOpenById(formId, flag - 1);
                return ResultUtils.success(ResultEnum.FORM_IS_NOT_OPEN.getCode(), ResultEnum.FORM_IS_NOT_OPEN.getMsg());
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
                return ResultUtils.success(ResultEnum.FORM_IS_ISSURE.getCode(), ResultEnum.FORM_IS_ISSURE.getMsg());
            } else {
                formMapper.updateFormIssureById(formId, flag - 1);
                return ResultUtils.success(ResultEnum.FORM_IS_NOT_ISSURE.getCode(), ResultEnum.FORM_IS_NOT_ISSURE.getMsg());
            }
        }
        return ResultUtils.fail(ResultEnum.FORM_ENTRY.getCode(), ResultEnum.FORM_ENTRY.getMsg());
    }

    @Override
    public Result getOpenForm(int formId) {
        //判断formId是否存在
        if (formMapper.checkFormById(formId)) {
            //判断是否公开
            int flag = formMapper.getFormOpenById(formId);
            if (flag == 0) {
                //未公开
                return ResultUtils.fail(ResultEnum.FORM_NOT_OPEN.getCode(), ResultEnum.FORM_NOT_OPEN.getMsg());
            } else {
                //已公开表单 返回表单信息
                return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), formMapper.getFormByFormId(formId));
            }
        }
        return ResultUtils.fail(ResultEnum.FORM_ENTRY.getCode(), ResultEnum.FORM_ENTRY.getMsg());
    }

    @Override
    public Result uploadImg(MultipartFile file) {

        if (!file.isEmpty()) {
            //文件不为空
//            log.info(excelFile.getName());  //返回参数名字  --excelFile
//            log.info(excelFile.getOriginalFilename());  //返回文件名字  --未命名报名_20201103203506.xlsx
//            log.info(excelFile.getContentType());  //返回文件内容类型  --application/vnd.openxmlformats-officedocument.spreadsheetml.sheet
//            log.info(String.valueOf(excelFile.getSize()));  //返回文件的大小,以字节为单位  --5788

            // 获取附件原名(有的浏览器如chrome获取到的是最基本的含 后缀的文件名,如myImage.png)
            // 获取附件原名(有的浏览器如ie获取到的是含整个路径的含后缀的文件名，如C:\\Users\\images\\myImage.png)
            String fileName = file.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
            String imgName = UUIDUtils.getUUID() + "." + fileType;

            //文件夹
            File file1 = new File(Constants.FilePath.FILE_IMG_HEAD);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            File newImg = new File(Constants.FilePath.FILE_IMG_HEAD + File.separator + imgName);
            // 如果该文件的上级文件夹不存在，则创建该文件的上级文件夹及其祖辈级文件夹
            if (newImg.getParentFile().exists()) {
                newImg.getParentFile().mkdirs();
            }
            try {
                // 将获取到的附件file,transferTo写入到指定的位置(即:创建dest时，指定的路径)
                file.transferTo(newImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResultUtils.success("上传成功", imgName);
        } else {
            return ResultUtils.fail("文件为空");
        }
    }
}