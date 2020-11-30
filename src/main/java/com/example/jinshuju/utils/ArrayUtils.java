package com.example.jinshuju.utils;

import java.util.Arrays;

/**
 * 数组工具类
 */
public class ArrayUtils {
    /**
     * 判断一个字符串是否存在于数组中
     *
     * @param targetString
     * @param targetStringArray
     * @return
     */
    public static boolean isHaveByString(String targetString, String[] targetStringArray) {
        return Arrays.asList(targetStringArray).contains(targetString);
    }

    /**
     * 判断一个数字是否存在于数组中
     *
     * @param targetInt
     * @param targetIntArray
     * @return
     */
    public static boolean isHaveByInt(Integer targetInt, Integer[] targetIntArray) {
        return Arrays.asList(targetIntArray).contains(targetInt);
    }
}
