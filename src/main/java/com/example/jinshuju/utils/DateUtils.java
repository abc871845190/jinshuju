package com.example.jinshuju.utils;


import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    /**
     * 给Date加时间
     *
     * @param date
     * @param addTime
     * @param flag
     * @return
     */
    public static Date addDateTime(Date date, int addTime, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(flag, addTime);// 24小时制
        return cal.getTime();
    }
}
