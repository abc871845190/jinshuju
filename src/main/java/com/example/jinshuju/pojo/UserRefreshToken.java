package com.example.jinshuju.pojo;

import lombok.Data;

@Data
public class UserRefreshToken {

  private int id;
  private int userId;
  private String refreshToken;
  private String token;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
}
