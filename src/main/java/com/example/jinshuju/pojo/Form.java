package com.example.jinshuju.pojo;

import lombok.Data;

@Data
public class Form {

  private long formId;
  private String formName;
  private String formTitle;
  private String formDesc;
  private java.sql.Timestamp formCreateTime;
  private java.sql.Timestamp formUpdateTime;
}
