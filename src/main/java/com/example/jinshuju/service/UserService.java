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
     * @param userid
     * @return
     */
    Result getUserInfo(int userid);

    /**
     * 检查用户邮箱是否存在
     *
     * @param email
     * @return
     */
    Result checkEmail(String email);

    /**
     * 检查用户名是否存在，用于检查是否注册
     *
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 修改密码
     *
     * @param user
     * @return
     */
    Result updatePsw(User user);

    /**
     * 判断用户原密码是否一样
     *
     * @param user
     * @return
     */
    Result checkPsw(User user);

    Result checkUpdateCode(User user);
}
