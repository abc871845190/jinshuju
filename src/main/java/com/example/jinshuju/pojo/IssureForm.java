package com.example.jinshuju.pojo;

import lombok.Data;

import java.util.List;

@Data
public class IssureForm {
    private String issureFormId; //表单模板id
    private String issureFormName; //表单模板名
    private String issureFormDesc; //表单模板描述
    private java.sql.Timestamp issureFormCreateTime; //表单模板创建时间
    private String issureFormTag; //表单模板标签
    private int issureFormViewCount; //表单模板被浏览量
    private String issureFormImg; //表单模板背景图
    private int issureFormLikeCount; //表单模板点赞量
    private int issureFormUseCount; //表单模板选用量
    private String issureFormScreenshot; //表单模板截图
    private List<Template> templateList; //组件列表
    private User user;  //作者
}
