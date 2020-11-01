package com.example.jinshuju.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "User")
@Data
public class User {
    @ApiModelProperty(value = "用户id", name = "userId", dataType = "int", example = "1")
    private int userId;
    @ApiModelProperty(value = "用户名", name = "userName", dataType = "String", example = "test")
    private String userName;
    @ApiModelProperty(value = "用户密码", name = "userPassword", dataType = "String", example = "123456")
    private String userPassword;
    @ApiModelProperty(value = "用户电话", name = "userTelephone", dataType = "String", example = "123465")
    private String userTelephone;
    @ApiModelProperty(value = "用户邮箱", name = "userEmail", dataType = "String", example = "123456@qq.com")
    private String userEmail;
    @ApiModelProperty(value = "用户头像", name = "userImg", dataType = "String", example = "http://www.baodu.com")
    private String userImg;
    private int userEmailStatus;
    private int userEmailKey;
    private java.sql.Timestamp userEmailKeyTime;
    @ApiModelProperty(value = "用户创建时间", name = "userCreateTime", dataType = "String", example = "2020-11-01 15:31:47")
    private java.sql.Timestamp userCreateTime;
    @ApiModelProperty(value = "用户更新时间", name = "userUpdateTime", dataType = "String", example = "2020-11-01 15:31:47")
    private java.sql.Timestamp userUpdateTime;
}
