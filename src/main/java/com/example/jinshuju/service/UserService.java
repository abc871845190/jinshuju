package com.example.jinshuju.service;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.utils.ResultUtils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户service 处理事务
 */
public interface UserService {

    /**
     * 注册
     *
     * @return
     */
    Result registerUser(User user);


    /**
     * 登录
     *
     * @param user     用户实体
     * @param request  请求
     * @param response 响应
     * @return
     */
    Result loginUser(User user, HttpServletRequest request, HttpServletResponse response);


    /**
     * 检查用户是否登录，有登陆返回用户信息
     *
     * @param request  请求
     * @param response 响应
     * @return 返回用户实例
     */
    User checkUserLogin(HttpServletRequest request, HttpServletResponse response);

    /**
     * 查看用户信息
     *
     * @param user
     * @return
     */
    Result getUser(User user, HttpServletRequest request, HttpServletResponse response);
}
