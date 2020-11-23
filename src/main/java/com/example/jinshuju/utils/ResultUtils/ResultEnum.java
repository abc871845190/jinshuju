package com.example.jinshuju.utils.ResultUtils;

/**
 * 返回枚举类
 */
public enum ResultEnum {
    //自定义code类型
    FAIL(2,"失败"),
    SUCCESS(1,"成功"),
    ERROR(0,"错误"),
    USER_EXISTS(10,"用户已存在"),
    USER_NOT_EXISTS(11,"用户不存在"),
    USER_NOT_LOGIN(900,"用户没有登陆"),
    FORM_IS_NOT_FAVOUR(21,"取消收藏成功"),
    FORM_IS_FAVOUR(20,"收藏成功");

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
