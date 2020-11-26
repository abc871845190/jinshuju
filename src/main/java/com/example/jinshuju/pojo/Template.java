package com.example.jinshuju.pojo;

import lombok.Data;

@Data
public class Template {

  private int templateId;
  private String templateName;
  private String templateDesc;
  private String templateTitle;
  private String templateContent;
  private String templateContentDefault;
  private int templateCount;
  private String templateDefault;
  private int formTemplateId; //和form绑定的id
  private String templateType; //组件构成内容类别
  private String templateIcon; //组件icon
}
