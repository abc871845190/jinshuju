package com.example.jinshuju.utils;

import java.util.UUID;

/**
 * uuid 唯一识别码工具类
 */
public class UUIDUtils {

    /**
     * 获取随机uuid 没有‘-’
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
