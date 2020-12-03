package com.example.jinshuju.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.jinshuju.mapper.DataMapper;
import com.example.jinshuju.mapper.FormMapper;
import com.example.jinshuju.mapper.UserMapper;
import com.example.jinshuju.pojo.*;
import com.example.jinshuju.service.FormService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.*;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
        form.setFormId(UUIDUtils.getRandomId(16));
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
        form.setFormCut(0);
        form.setFormCutTime(new Timestamp(new Date().getTime()));
        //插入form，获取id
        //log.info(form.toString());
        return doCreateForm(user, form);
    }

    private Result doCreateForm(User user, Form form) throws IOException, WriterException {
        String formId = form.getFormId();
        if (formMapper.insertForm(user, form)) {
            //插入成功，原form实例添加id
            //生成相应公开访问填写的url 以及二维码
            String formUrl = "/f/" + formId;
            form.setFormUrl(formUrl);
            String QRCode = "/img/QRCode/" + QRCodeUtils.createImage(Constants.Url.host + "/f/" + formUrl);
//            log.info(QRCodePath);
            form.setFormQRCode(QRCode);
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
        String formId = form.getFormId();
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
                insertOldAndNewTemplateList(dataList, formId, haveOldTemplateList, haveNewTemplateList);
                return ResultUtils.success("插入表单成功", form);
            } else {
                insertOldAndNewTemplateList(dataList, formId, haveOldTemplateList, haveNewTemplateList);
                return ResultUtils.success("插入表单成功");
            }
        }
    }

    /**
     * 分别将新的组件和旧的组件插入 以及备份的data数据
     *
     * @param dataList
     * @param formId
     * @param haveOldTemplateList
     * @param haveNewTemplateList
     */
    private void insertOldAndNewTemplateList(List<Data> dataList, String formId, List<Template> haveOldTemplateList, List<Template> haveNewTemplateList) {
        //判断旧的是否有组件
        if (haveOldTemplateList.size() != 0) {
            //先插入旧的
            formMapper.insertOldTemplate(haveOldTemplateList, formId);
        }
        //判断是否有新的组件  插入新的组件
        if (haveNewTemplateList.size() != 0) {
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
    }

    @Override
    public Result getForms(User user, int flag) {
        //获取用户id
        int userId = user.getUserId();
        if (userMapper.checkUserExist(userId) != null) {
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
    public Result getForm(String formId) {
        if (TextUtils.isEmpty(formId)) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        //判断id是否存在
        if (formMapper.checkFormById(formId) != null) {
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
    public Result copyForm(User user, String formId) throws IOException, WriterException {
        //判0
        if (TextUtils.isEmpty(formId)) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        //获取表单id的所有表单信息
        Form form = formMapper.getFormByFormId(formId);
        if (form != null) {
            //更新复制表单数据
            form.setFormId(UUIDUtils.getRandomId(16));
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
    public Result deleteForm(String formId) {
        //判断id是否存在
        if (formMapper.checkFormById(formId) != null) {
            //删除二维码文件 /img/QRCode/18fd0786fbf644eb94a7aa8c5c0cefa6.jpg
            String QRCode = formMapper.getFormQRCodeById(formId);
            FileUtils.deleteFile(Constants.FilePath.FILE_IMG_QRCODE, QRCode.substring(QRCode.lastIndexOf("/") + 1));
            //TODO:如果有图片数据  删除图片数据
            List<Template> templateList = formMapper.getTemplatesByFormId(formId);
            if (templateList != null && templateList.size() != 0) {
                //有组件
                for (Template t : templateList) {
                    if (t.getTemplateId() == 5 || t.getTemplateId() == 6) {
                        //获取templateImgUrl
                        String templateImgUrl = formMapper.getTemplateImgUrlByFormTemplateId(t.getFormTemplateId());
                        if (!TextUtils.isEmpty(templateImgUrl)) {
                            //解析
                            List<DataBean> templateImgUrlList = JSON.parseArray(templateImgUrl, DataBean.class);
                            if (templateImgUrlList.size() != 0) {
                                for (DataBean db : templateImgUrlList) {
                                    //http://jinshuju.max3.fgnwctvip.com/img/TemplateImg/d4e4579a888d45ad9943871e24f0e9ed.gif
                                    String value = db.getValue();
                                    if (!TextUtils.isEmpty(value)) {
                                        String imgName = value.substring(value.lastIndexOf("/") + 1);
                                        if (!TextUtils.isEmpty(imgName)) {
                                            //删除
                                            FileUtils.deleteFile(Constants.FilePath.FILE_IMG_TEMPLATE, imgName);
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
            boolean flag = formMapper.deleteFormById(formId);
            return flag == true ? ResultUtils.success("删除表单成功") : ResultUtils.fail("删除表单失败");
        }
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
    }

    @Override
    public Result updateFormName(String formId, String formName) {
        //判断为空
        if (TextUtils.isEmpty(formName)) {
            return ResultUtils.fail("表单名为空");
        }
        if (TextUtils.isEmpty(formId)) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        if (checkFormId(formId)) {
            Boolean flag = formMapper.updateFormNameById(formId, formName, new Timestamp(System.currentTimeMillis()));
            return flag == true ? ResultUtils.success(ResultEnum.FORM_NAME_CHANGE.getCode(), ResultEnum.FORM_NAME_CHANGE.getMsg()) : ResultUtils.fail(ResultEnum.FORM_NAME_REPEAT.getCode(), ResultEnum.FORM_NAME_REPEAT.getMsg());
        }
        return ResultUtils.fail(ResultEnum.FORM_EMTRY.getCode(), ResultEnum.FORM_EMTRY.getMsg());
    }

    @Override
    public Result updateFormTag(String formId, String formTag) {
        //判断为空
        if (TextUtils.isEmpty(formTag)) {
            return ResultUtils.fail("表单名为空");
        }
        if (TextUtils.isEmpty(formId)) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        if (checkFormId(formId)) {
            Boolean flag = formMapper.updateFormTagById(formId, formTag);
            return flag == true ? ResultUtils.success("修改表单tag成功") : ResultUtils.fail("表单tag重复");
        }
        return ResultUtils.fail("表单id不存在");
    }

    @Override
    public Boolean checkFormId(String formId) {
        return formMapper.checkFormById(formId) != null;
    }

    @Override
    public void updateFormView(String formId) {
        //TODO:判断表单是否公开
        //TODO:+1浏览量
    }

    @Override
    public Result updateFormOpen(String formId) {
        //判断id是否存在
        if (formMapper.checkFormById(formId) != null) {
            //数据库拿open数字
            int flag = formMapper.getFormOpenById(formId);
            if (flag == 0) {
                formMapper.updateFormOpenById(formId, flag + 1, new Timestamp(System.currentTimeMillis()));
                return ResultUtils.success(ResultEnum.FORM_IS_OPEN.getCode(), ResultEnum.FORM_IS_OPEN.getMsg());
            } else {
                formMapper.updateFormOpenById(formId, flag - 1, new Timestamp(System.currentTimeMillis()));
                return ResultUtils.success(ResultEnum.FORM_IS_NOT_OPEN.getCode(), ResultEnum.FORM_IS_NOT_OPEN.getMsg());
            }
        }
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
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
    public Result getOpenFormUrl(String formId) {
        //判断formId是否存在
        if (formMapper.checkFormById(formId) != null) {
            return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), formMapper.getFormUrlById(formId));
        }
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
    }

    @Override
    public void createQRCode(String formId, HttpServletResponse response) {
        if (formMapper.checkFormById(formId) != null) {
            String QRcodePath = formMapper.getFormQRCodeById(formId);
            //TODO:....
        }
    }

    @Override
    public Result updateFormFavour(String formId) {
        //判断formid是否存在
        if (formMapper.checkFormById(formId) != null) {
            //数据库拿标识int
            int flag = formMapper.getFormFavourById(formId);
            if (flag == 0) {
                formMapper.updateFormFavourById(formId, flag + 1, new Timestamp(System.currentTimeMillis()));
                return ResultUtils.success(
                        ResultEnum.FORM_IS_FAVOUR.getCode(),
                        ResultEnum.FORM_IS_FAVOUR.getMsg());
            } else {
                formMapper.updateFormFavourById(formId, flag - 1, new Timestamp(System.currentTimeMillis()));
                return ResultUtils.success(
                        ResultEnum.FORM_IS_NOT_FAVOUR.getCode(),
                        ResultEnum.FORM_IS_NOT_FAVOUR.getMsg());
            }
        }
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
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
    public Result getTemplates(String formId) {
        //判断id
        if (formMapper.checkFormById(formId) != null) {
            List<Template> templateList = formMapper.getTemplatesByFormId(formId);
            return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), templateList);
        }
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
    }

    @Override
    public Result updateFormIssure(String formId) {
        //判断formid是否存在
        if (formMapper.checkFormById(formId) != null) {
            //数据库拿标识int
            int flag = formMapper.getFormIssureById(formId);
            if (flag == 0) {
                formMapper.updateFormIssureById(formId, flag + 1, new Timestamp(System.currentTimeMillis()));
                return ResultUtils.success(ResultEnum.FORM_IS_ISSURE.getCode(), ResultEnum.FORM_IS_ISSURE.getMsg());
            } else {
                formMapper.updateFormIssureById(formId, flag - 1, new Timestamp(System.currentTimeMillis()));
                return ResultUtils.success(ResultEnum.FORM_IS_NOT_ISSURE.getCode(), ResultEnum.FORM_IS_NOT_ISSURE.getMsg());
            }
        }
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
    }

    @Override
    public Result getOpenForm(String formId) {
        //判断formId是否存在
        if (formMapper.checkFormById(formId) != null) {
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
        return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
    }

    @Override
    public Result uploadImg(MultipartFile file) {
        if (!file.isEmpty()) {
//            try {
//                InputStream is = file.getInputStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
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
            File file1 = new File(Constants.FilePath.FILE_IMG_TEMPLATE);
            if (!file1.exists()) {
                file1.mkdirs();
            }

            File newImg = new File(Constants.FilePath.FILE_IMG_TEMPLATE + File.separator + imgName);
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
            return ResultUtils.success("上传成功", File.separator + "img" + File.separator + "TemplateImg" + File.separator + imgName);
        } else {
            return ResultUtils.fail("文件为空");
        }
    }

    @Override
    public Result deleteImg(String fileUrl) {
        //拼接   --/img/head/default_img.jpg
        return FileSystemUtils.deleteRecursively(new File(Constants.FilePath.FILE + fileUrl)) == true ? ResultUtils.success("删除成功") : ResultUtils.fail("删除失败");
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
        String formId = form.getFormId();
        //获取原来组件list
        List<Template> templateList = formMapper.getTemplatesByFormId(formId);
        //新组件list
        List<Template> newTemplateList = form.getTemplateList();
        //删除原组件与新组件不同的组件 新的不管 旧的全删，更新对应组件修改内容以及数据项内容
        updateOldTemplate(templateList, newTemplateList);
        //备份原来绑定组件list的数据
        List<Data> dataList = dataMapper.getAllDataByFormId(formId);
        //删除与form绑定的组件
        formMapper.deleteTemplateList(formId);
        //插入新的组件list
        return insertTemplateList(form, dataList, 1);
    }

    @Override
    public Result deleteFormContentAndData(int formTemplateId, String mapItem) {
        //判空
        if (formTemplateId == 0) {
            return ResultUtils.fail("表单绑定组件id为空");
        }
        if (TextUtils.isEmpty(mapItem)) {
            return ResultUtils.fail("contentItem为空");
        }

        //将json字符串转变为bean类
        //log.info(mapItem);
        DataBean dataBean = JSON.parseObject(mapItem, DataBean.class);

        //模拟数据
        //String radio = "{key:\"0\",value:\"xx\"}";
        //String checkbox = "[{key:\"0\",value:\"xx\"},{key:\"1\",value:\"xxx\"},{key:\"2\",value:\"啊啊啊\"}]";
        //首先判断组件类型
        int templateType = formMapper.getTemplateTypeByFormTemplateId(formTemplateId);

        if (ArrayUtils.isHaveByInt(templateType, Constants.Array.MultiSelect)) {
            //组件类型为多选
            if (templateType == 6) {
                //图片多选
                updateTemplate(formTemplateId, dataBean, 1);
            } else {
                //纯多选
                updateTemplate(formTemplateId, dataBean, 0);
            }
            //获取该表单绑定组件id的所有数据项
            List<Data> dataBeanList = dataMapper.getAllDataByFormTemplateId(formTemplateId);
            if (dataBeanList != null && dataBeanList.size() != 0) {
                //有填数据
                //遍历
                for (Data d : dataBeanList) {
                    //遍历details
                    for (DataDetails dd : d.getDataDetailsList()) {
                        //获取content
                        String dataContent = dd.getDataContent();
                        if (!TextUtils.isEmpty(dataContent)) {
                            //解析内容
                            List<DataBean> newDataContentBeanList = parseDataBean(dataBean, dataContent);
                            //序列化list
                            String newDataContent = JSON.toJSONString(newDataContentBeanList);
                            //设置新的数据进去
                            dd.setDataContent(newDataContent);
                        }
                    }
                }
                //遍历完成
                //更新数据库数据
                dataMapper.updateDataDetailsList(dataBeanList);
            }
            return ResultUtils.success("删除成功");
        }
        if (ArrayUtils.isHaveByInt(templateType, Constants.Array.multipleChoice)) {
            //组件类型为单选
            //判断是否为图片单选
            if (templateType == 5) {
                updateTemplate(formTemplateId, dataBean, 1);
            } else {
                //纯单选
                updateTemplate(formTemplateId, dataBean, 0);
            }
            //直接删除绑定该组件的选项key的数据项
            dataMapper.deleteDataDetailsByFormTemplateIdAndContent(formTemplateId, dataBean.getKey());
            return ResultUtils.success("删除成功");
        }
        return ResultUtils.success("删除失败");
    }

    @Override
    public Result judgeForm(int userId, String formId) {
        if (TextUtils.isEmpty(formId)) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        return formMapper.judgeForm(userId, formId) != null ? ResultUtils.success(ResultEnum.TRUE.getCode(), ResultEnum.TRUE.getMsg()) : ResultUtils.success(ResultEnum.FALSE.getCode(), ResultEnum.FALSE.getMsg());
    }

    @Override
    public Result getFormTags(String formId) {
        if (formMapper.checkFormById(formId) == null) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        return ResultUtils.success(ResultEnum.SUCCESS.getMsg(), formMapper.getFormTagsByFormId(formId));
    }

    @Override
    public Result judgeFormOpen(String formId) {
        if (formMapper.checkFormById(formId) == null) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        return formMapper.getFormOpenById(formId) == 0 ? ResultUtils.success(ResultEnum.FALSE.getCode(), ResultEnum.FALSE.getMsg()) : ResultUtils.success(ResultEnum.TRUE.getCode(), ResultEnum.TRUE.getMsg());
    }

    @Override
    public Result judgeFormCut(String formId) {
        if (formMapper.checkFormById(formId) == null) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        int flag = formMapper.getFormCutByFormId(formId);
        if (flag == 0) {
            return ResultUtils.success("没有开启截止功能");
        } else {
            //对比截止时间
            boolean isCut = formMapper.judgeFormCutTime(formId); //0未过期 1过期
            if (isCut) {
                //过期
                //关闭截止和开启填写功能
                formMapper.updateFormCutAndOpen(formId);
                return ResultUtils.success("表单截止时间已过期，已关闭截止和开启填写标识！");
            } else {
                //未过期
                return ResultUtils.success("表单截止时间未过期");
            }
        }
    }

    @Override
    public Result updateFormCut(String formId) {
        if (formMapper.checkFormById(formId) == null) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        int flag = formMapper.getFormCutByFormId(formId);
        if (flag == 0) {
            formMapper.updateFormCutByFormId(formId, flag + 1, new Timestamp(DateUtils.addDateTime(new Date(), 2, Calendar.HOUR).getTime()));
            return ResultUtils.success("表单开启截止");
        } else {
            formMapper.updateFormCutByFormId(formId, flag - 1, new Timestamp(new Date().getTime()));
            return ResultUtils.success("表单关闭截止");
        }
    }

    @Override
    public Result updateFormCutTime(String formId, Date date) {
        if (formMapper.checkFormById(formId) == null) {
            return ResultUtils.fail(ResultEnum.FORM_ID_EMTRY.getCode(), ResultEnum.FORM_ID_EMTRY.getMsg());
        }
        formMapper.updateFormCutTime(formId, new Timestamp(date.getTime()));
        return ResultUtils.success();
    }

    /**
     * 删除组件对应的content字段，还有form-img-url相应的链接
     *
     * @param formTemplateId
     * @param dataBean
     * @param flag           0为纯选择 1为图片选择
     */
    private void updateTemplate(int formTemplateId, DataBean dataBean, int flag) {
        //删除组件content相应的字段
        Template template = formMapper.getTemplateByFormTemplateId(formTemplateId);
        //解析
        List<DataBean> newContentList = parseDataBean(dataBean, template.getTemplateContent());
        //序列化list
        String newTemplateContent = JSON.toJSONString(newContentList);
        //设置数据
        template.setTemplateContent(newTemplateContent);
        if (flag == 1) {
            //删除组件图片url相应的字段
            //解析
            List<DataBean> newFormImgUrlList = parseDataBean(dataBean, template.getTemplateImgUrl());
            //序列化list
            String newFormImgUrl = JSON.toJSONString(newFormImgUrlList);
            //设置数据
            template.setTemplateImgUrl(newFormImgUrl);
        }
        //更新template
        formMapper.updateTemplate(template);
    }

    /**
     * 删除对应的content内容
     *
     * @param dataBean
     * @param templateContent
     * @return
     */
    private List<DataBean> parseDataBean(DataBean dataBean, String templateContent) {

        List<DataBean> oldContentList = JSON.parseArray(templateContent, DataBean.class);
        //log.info("------------------------old:"+oldContentList.toString());
        List<DataBean> newContentList = new ArrayList<>();
        for (DataBean db : oldContentList) {
            if (db.getKey() != dataBean.getKey()) {
                newContentList.add(db);
            }
        }
        // log.info("------------------------new:"+newContentList.toString());
        return newContentList;
    }

    /**
     * 删除原组件与新组件不同的组件 新的不管 旧的全删
     *
     * @param templateList
     * @param newTemplateList
     */
    private void updateOldTemplate(List<Template> templateList, List<Template> newTemplateList) {
        //遍历
        boolean flag = false;
        log.info("-------------------------------------------------------------------");
        log.info("updateOldTemplate   ==>  newTemplateList  ==>  " + newTemplateList.toString());
        log.info("-------------------------------------------------------------------");
        log.info("updateOldTemplate   ==>  oldTemplateList  ==>  " + templateList.toString());
        for (Template oldTemplate : templateList) {
            for (Template newTemplate : newTemplateList) {
                //（1）如果有formTemplateId 循环遍历查找原来组件list是否有这个id 即判断是否为0
                if (newTemplate.getFormTemplateId() == 0) {
                    //（2）如果没有formTemplateId 即为新的组件 没有动作
                } else {
                    // （3）有就是先对比是否删减旧的组件
                    int oldFormTemplateId = oldTemplate.getFormTemplateId();
                    int newFormTemplateId = newTemplate.getFormTemplateId();
                    if (oldFormTemplateId == newFormTemplateId) {
                        //对比一样的话 即新的组件有旧的组件 设置标识
                        flag = true;
                        //-------------------------------------------------------------------------------------------------//
                        //判断组件的类型
                        int templateType = formMapper.getTemplateTypeByFormTemplateId(oldFormTemplateId);
                        if (ArrayUtils.isHaveByInt(templateType, Constants.Array.MultiSelect)) {
                            //多选
                            updateDataContent(oldTemplate, newTemplate, oldFormTemplateId, 0);
                        }
                        if (ArrayUtils.isHaveByInt(templateType, Constants.Array.multipleChoice)) {
                            //单选
                            updateDataContent(oldTemplate, newTemplate, oldFormTemplateId, 1);
                        }
                        //-------------------------------------------------------------------------------------------------//
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
     * 修改相应数据项内容
     *
     * @param oldTemplate
     * @param newTemplate
     * @param oldFormTemplateId
     * @param flag              0为多选   1为单选
     */
    private void updateDataContent(Template oldTemplate, Template newTemplate, int oldFormTemplateId, int flag) {
        List<Data> dataList = dataMapper.getAllDataByFormTemplateId(oldFormTemplateId);
        if (dataList != null && dataList.size() != 0) {
            log.info("-------------------------------------------------------------------");
            log.info("updateDataContent  ==>  dataList  ==>  " + dataList.toString());
            //解析组件content
            List<DataBean> oldTemplateContent = JSON.parseArray(oldTemplate.getTemplateContent(), DataBean.class);
            List<DataBean> newTemplateContent = JSON.parseArray(newTemplate.getTemplateContent(), DataBean.class);
            log.info("-------------------------------------------------------------------");
            log.info("updateDataContent  ==>  oldTemplateContent  ==>  " + oldTemplateContent.toString());
            log.info("-------------------------------------------------------------------");
            log.info("updateDataContent  ==>  newTemplateContent  ==>  " + newTemplateContent.toString());
            //遍历
            for (DataBean oldContent : oldTemplateContent) {
                for (DataBean newContent : newTemplateContent) {
                    //匹配到一样的key值
                    if (oldContent.getKey() == newContent.getKey()) {
                        //直接拿新的值和键
                        int keyValue = newContent.getKey();
                        String newValue = newContent.getValue();
                        //匹配数据list
                        for (Data d : dataList) {
                            for (DataDetails dd : d.getDataDetailsList()) {
                                //解析dataContent
                                String dataContent = dd.getDataContent();
                                String newDataContent = null;
                                if (flag == 0) {
                                    List<DataBean> dataBeanList = JSON.parseArray(dataContent, DataBean.class);
                                    //遍历
                                    for (DataBean db : dataBeanList) {
                                        if (db.getKey() == keyValue) {
                                            //有该选项，直接覆盖新的值上去
                                            db.setValue(newValue);
                                        }
                                    }
                                    //序列化list
                                    newDataContent = JSON.toJSONString(dataBeanList);
                                } else {
                                    DataBean db = JSON.parseObject(dataContent, DataBean.class);
                                    log.info(db.toString());
                                    if (db.getKey() == keyValue) {
                                        //覆盖
                                        db.setValue(newValue);
                                    }
                                    //序列化
                                    newDataContent = JSON.toJSONString(db);
                                }
                                //设置新的dataContent
                                dd.setDataContent(newDataContent);
                            }
                        }
                    }
                }
            }
            //向数据库更新相关的数据项信息
            dataMapper.updateDataDetailsList(dataList);
        } else {
            //没有数据   无动作
        }
    }
}