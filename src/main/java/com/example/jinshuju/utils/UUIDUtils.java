package com.example.jinshuju.utils;

import java.util.Random;
import java.util.UUID;

/**
 * uuid 唯一识别码工具类
 */
public class UUIDUtils {

    /**
     * 获取随机uuid 没有‘-’
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成 62^size 重复率的字符串id
     *
     * @param size
     * @return
     */
    public static String getRandomId(int size) {
        char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
                'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
                'Z'};
        Random random = new Random();
        char[] cs = new char[size];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = digits[random.nextInt(digits.length)];
        }

        return new String(cs);
    }
}
