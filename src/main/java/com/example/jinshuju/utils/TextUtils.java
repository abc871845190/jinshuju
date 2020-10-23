package com.example.jinshuju.utils;

public class TextUtils {

    /**
     * 是否为空
     *
     * @param text
     * @return
     */
    public static boolean isEmpty(String text) {
        return text == null || text.length() == 0;
    }

    /**
     * 生产五个不重复数字验证码
     *
     * @return
     */
    public static int RandomCode() {
        //10000 - 99999 返回
        return (int) Math.round(Math.random()*89999+10000);
    }
}
