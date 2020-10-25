package com.example.jinshuju.pojo;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class User {

    private int userId;
    private String userName;
    private String userPassword;
    private String userTelephone;
    private String userEmail;
    private String userImg;
    private int userEmailStatus;
    private int userEmailKey;
    private java.sql.Timestamp userEmailKeyTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp userCreateTime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private java.sql.Timestamp userUpdateTime;
}
