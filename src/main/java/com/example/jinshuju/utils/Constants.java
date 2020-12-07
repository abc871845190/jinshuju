package com.example.jinshuju.utils;

/**
 * 常数类
 */
public interface Constants {
    interface User {
        String KEY_TOKEN = "key_token_";
        String COOKIES_TOKEN = "cookies_token";
        String HEADER_TOKEN = "header_token";
    }

    interface TimeValue {
        int ONE_MIN = 60;
        int ONE_HOUR = 60 * 60;
        int ONE_DAY = 60 * 60 * 24;
        int ONE_WEEK = 60 * 60 * 24 * 7;
        int ONE_MONTH = 60 * 60 * 24 * 30;
        int ONE_YEAR = 60 * 60 * 24 * 365;
    }

    interface FileType {
        String FILE_TYPE_XLS = "xls";
        String FILE_TYPE_XLSX = "xlsx";
        String FILE_TYPE_JPG = "jpg";
        String FILE_TYPE_PNG = "png";
    }

    interface Encode{
        String UTF8 = "utf-8";
    }

    interface FilePath{
        String FILE_IMG_HEAD = "D:\\eclipse for ee\\jinshuju\\src\\main\\resources\\static\\img\\head";
        String FILE_IMG_TEMPLATE = "D:\\eclipse for ee\\jinshuju\\src\\main\\resources\\static\\img\\TemplateImg";
        String FILE_IMG = "D:\\eclipse for ee\\jinshuju\\src\\main\\resources\\static\\img";
        String FILE = "D:\\eclipse for ee\\jinshuju\\src\\main\\resources\\static";
        String FILE_IMG_QRCODE = "D:\\eclipse for ee\\jinshuju\\src\\main\\resources\\static\\img\\QRCode";
    }

    interface Array{
        Integer[] MultiSelect = {1,6};  //多选
        Integer[] multipleChoice = {0,4,5};  //单选
    }

    interface Url{
        String host = "jinshuju.max3.fgnwctvip.com";
    }
}
