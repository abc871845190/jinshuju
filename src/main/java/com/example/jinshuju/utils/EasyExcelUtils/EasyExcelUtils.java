package com.example.jinshuju.utils.EasyExcelUtils;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.poi.ss.formula.functions.T;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * easyExcel框架封装工具类
 * https://www.cnblogs.com/JWMA/p/12768166.html
 */
public class EasyExcelUtils {

    /**
     * 同步无模型 的 读（默认读取sheet0，从第二行开始读）
     *
     * @param filePath excel文件的绝对路径
     * @return
     */
    public static List<Map<Integer, String>> syncRead(String filePath) {
        return EasyExcelFactory.read(filePath).sheet().doReadSync();
    }


    /**
     * 同步无模型读（自定义读取sheetX，从第2行开始读）
     *
     * @param filePath excel文件的绝对路径
     * @param sheetNo  sheet页号，从0开始
     * @return
     */
    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).doReadSync();
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     *
     * @param filePath   excel文件的绝对路径
     * @param sheetNo    sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @return
     */
    public static List<Map<Integer, String>> syncRead(String filePath, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(filePath).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     *
     * @param inputStream
     * @param sheetNo
     * @param headRowNum
     * @return
     */
    public static List<Map<Integer, String>> syncRead(InputStream inputStream, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(inputStream).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 同步无模型读（指定sheet和表头占的行数）
     *
     * @param file
     * @param sheetNo
     * @param headRowNum
     * @return
     */
    public static List<Map<Integer, String>> syncRead(File file, Integer sheetNo, Integer headRowNum) {
        return EasyExcelFactory.read(file).sheet(sheetNo).headRowNumber(headRowNum).doReadSync();
    }

    /**
     * 异步无模型读（默认读取sheet0,从第2行开始读）
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param filePath 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener){
        EasyExcelFactory.read(filePath, excelListener).sheet().doRead();
    }

    /**
     * 异步无模型读（默认表头占一行，从第2行开始读）
     * @param filePath 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener, Integer sheetNo){
        EasyExcelFactory.read(filePath, excelListener).sheet(sheetNo).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param inputStream
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(InputStream inputStream, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(inputStream, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param file
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     */
    public static void asyncRead(File file, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(file, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }

    /**
     * 异步无模型读（指定sheet和表头占的行数）
     * @param filePath
     * @param excelListener 监听器，在监听器中可以处理行数据LinkedHashMap，表头数据，异常处理等
     * @param sheetNo sheet页号，从0开始
     * @param headRowNum 表头占的行数，从0开始（如果要连表头一起读出来则传0）
     * @return
     */
    public static void asyncRead(String filePath, AnalysisEventListener<T> excelListener, Integer sheetNo, Integer headRowNum){
        EasyExcelFactory.read(filePath, excelListener).sheet(sheetNo).headRowNumber(headRowNum).doRead();
    }
}
