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
        return (int) Math.round(Math.random() * 89999 + 10000);
    }

    /**
     * 分割String字符串
     *
     * @param targetString
     * @param regex
     * @return
     */
    public static String[] splitString(String targetString, String regex) {
        return targetString.split(regex);
    }

    /**
     * 分割json数组字符串  生成分割完之后的数组
     *
     * @param targetString 目标字符串
     * @param regex        分隔符
     * @return
     */
    public static String[] splitJsonString(String targetString, String regex) {
        String subTargetString = targetString.substring(1, targetString.length() - 1);
        String[] targetStringArray = subTargetString.split(regex);
        for (String item : targetStringArray) {
            String newItem = item.substring(1, item.length() - 1);
            item = newItem;
        }
        return targetStringArray;
    }

    /**
     * 判断字段是否含有某个字段
     *
     * @param targetString
     * @param subString
     * @return
     */
    public static boolean indexOfStr(String targetString, String subString) {
        return targetString.contains(subString);
    }

    /**
     * 判断字段是否含有某个字段
     *
     * @param targetString
     * @param subString
     * @param startIndex   开始扫描的位置
     * @return
     */
    public static boolean indexOfStr(String targetString, String subString, int startIndex) {
        return targetString.indexOf(subString, startIndex) >= 0;
    }
}
