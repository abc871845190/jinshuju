package com.example.jinshuju.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.List;

@Data
public class Form {

  private long formId;
  private String formName;
  private String formTitle;
  private String formDesc;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.sql.Timestamp formCreateTime;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.sql.Timestamp formUpdateTime;
  private List<Template> templateList;
}
