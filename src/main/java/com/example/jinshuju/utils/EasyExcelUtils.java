package com.example.jinshuju.utils;

import com.alibaba.excel.EasyExcelFactory;

import java.util.List;
import java.util.Map;

/**
 * easyExcel框架封装工具类
 * https://www.cnblogs.com/JWMA/p/12768166.html
 */
public class EasyExcelUtils {

    /**
     * 同步无模型 的 读（默认读取sheet0，从第二行开始读）
     * @param filePath
     * @return
     */
    public static List<Map<Integer,String>> syncRead(String filePath){
        return EasyExcelFactory.read(filePath).sheet().doReadSync();
    }
}
