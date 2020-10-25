package com.example.jinshuju.pojo;


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
    private java.sql.Timestamp userCreateTime;
    private java.sql.Timestamp userUpdateTime;
}
