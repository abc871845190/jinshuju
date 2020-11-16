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
  //和form绑定的id
  private int formTemplateId;
  private String templateType;
}
