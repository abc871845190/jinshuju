package com.example.jinshuju.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cookies工具类
 */
public class CookiesUtils {

    //默认一年
    public static final int DEFAULT_AGE = 60 * 60 * 24 * 365;

    public static final String DOMAIN = "localhost";

    /**
     * 设置cookies
     *
     * @param response
     * @param name
     * @param value
     * @param time
     * @param path
     */
    public static void setCookies(HttpServletResponse response, String name, String value, int time, String path) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(time);
        cookie.setPath(path);
        cookie.setDomain(DOMAIN);
        response.addCookie(cookie);
    }

    public static void setCookies(HttpServletResponse response, String name, String value, String path) {
        setCookies(response, name, value, DEFAULT_AGE, path);
    }

    public static void setCookies(HttpServletResponse response, String name, String value, int time) {
        setCookies(response, name, value, time, null);
    }

    /**
     * 获取某个cookie的值
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String cookieName) {
        //Cookie的默认有效期是一次会话中。
        Cookie[] cookies = request.getCookies();
        String value = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                //遍历出每一个cookie对象，咱们怎么去判断该cookie是否是我们想要的那个呢?
                //通过cookie的name进行判断
                String name = cookie.getName();
                if (name.equals(cookieName)) {
                    //获取cookie的value
                    value = cookie.getValue();
                }
            }
        }
        return value;
    }

    /**
     * 删除指定cookies
     *
     * @param request
     * @param response
     * @param name
     */
    public static void deleteCookies(HttpServletRequest request, HttpServletResponse response, String name) {
        setCookies(response, name, null, 0);
    }
}
