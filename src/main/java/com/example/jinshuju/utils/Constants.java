package com.example.jinshuju.utils;

/**
 * 常数类
 */
public interface Constants {
    interface User {
        String KEY_TOKEN = "key_token_";
        String COOKIES_TOKEN = "cookies_token";
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
    }
}
