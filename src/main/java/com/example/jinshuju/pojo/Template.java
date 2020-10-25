package com.example.jinshuju.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Template {

  private int templateId;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private String templateName;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private String templateDesc;

}
