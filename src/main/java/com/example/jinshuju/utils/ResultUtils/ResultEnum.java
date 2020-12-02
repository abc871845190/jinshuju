package com.example.jinshuju.utils.ResultUtils;

/**
 * 返回枚举类
 */
public enum ResultEnum {
    //自定义code类型
    FAIL(2,"失败"),
    SUCCESS(1,"成功"),
    ERROR(0,"错误"),
    TRUE(3,"是"),
    FALSE(4,"否"),
    USER_EXISTS(10,"用户已存在"),
    USER_NOT_EXISTS(11,"用户不存在"),
    USER_NOT_LOGIN(900,"用户没有登陆"),
    FORM_IS_NOT_FAVOUR(21,"取消收藏成功"),
    FORM_IS_FAVOUR(20,"收藏成功"),
    FORM_NAME_REPEAT(22,"表单名重复"),
    FORM_NAME_EMTRY(23,"表单名为空"),
    FORM_NAME_CHANGE(24,"表单名修改成功"),
    FORM_EMTRY(25,"表单不存在"),
    FORM_NOT_OPEN(26,"表单未发布"),
    FORM_IS_ISSURE(27,"表单发布为模版成功"),
    FORM_IS_NOT_ISSURE(28,"取消表单发布为模版成功"),
    FORM_IS_OPEN(29,"表单发布填写成功"),
    FORM_IS_NOT_OPEN(30,"取消表单发布填写成功"),
    FORM_ID_EMTRY(31,"表单id为空");


    private int code;
    private String msg;


    ResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
