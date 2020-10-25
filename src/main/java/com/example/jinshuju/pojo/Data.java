package com.example.jinshuju.pojo;

import com.alibaba.fastjson.annotation.JSONField;

@lombok.Data
public class Data {

  private long dataId;
  private Form form;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.sql.Timestamp dataCreateTime;
}
