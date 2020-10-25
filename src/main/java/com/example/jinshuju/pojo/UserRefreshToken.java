package com.example.jinshuju.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class UserRefreshToken {

  private int id;
  private int userId;
  private String refreshToken;
  private String token;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.sql.Timestamp createTime;
  @JSONField(format = "yyyy-MM-dd HH:mm:ss")
  private java.sql.Timestamp updateTime;
}
