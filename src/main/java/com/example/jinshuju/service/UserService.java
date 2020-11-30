package com.example.jinshuju.service;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.utils.ResultUtils.Result;
import org.springframework.web.multipart.MultipartFile;

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
     * @return
     */
    Result getUserInfo(User user);

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
     * @param userid
     * @param originPsw
     * @param newPsw
     * @return
     */
    Result updatePsw(int userid, String originPsw, String newPsw);

    /**
     * 检查原密码是否一致，用于修改密码
     *
     * @param userPsw
     * @param originPsw
     * @return
     */
    boolean checkPsw(String userPsw, String originPsw);

    /**
     * 修改邮箱
     *
     * @param user
     * @return
     */
    Result updateEmail(User user);

    /**
     * 修改电话
     *
     * @param user
     * @return
     */
    Result updateTelephone(User user);

    /**
     * 注销
     *
     * @return
     */
    Result logout(HttpServletRequest request, HttpServletResponse response);

    Result checkUpdateCode(User user);

    /**
     * 上传头像 返回路径
     *
     * @param file
     * @param user
     * @return
     */
    Result uploadHeadImg(MultipartFile file, User user);
}
