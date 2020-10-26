package com.example.jinshuju.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "User")
@Data
public class User {
    private int userId;
    @ApiModelProperty(value = "用户名", name = "userName", dataType = "String", required = true, example = "test")
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
