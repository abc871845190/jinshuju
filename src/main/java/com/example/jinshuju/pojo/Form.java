package com.example.jinshuju.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Form {

  private long formId;
  private String formName;
  private String formTitle;
  private String formDesc;
  private java.sql.Timestamp formCreateTime;
  private java.sql.Timestamp formUpdateTime;
  private String formTag;
  private long formOpen;
  private String formUrl;
  private long formOpened;
  private long formResultOpened;
  private long formType;

  private List<Template> templateList;
}
