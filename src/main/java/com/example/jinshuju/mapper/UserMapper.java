package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;

@Mapper
public interface UserMapper {
    /**
     * 注册用户
     *
     * @param user
     */
    void insertUser(User user);

    /**
     * 检查用户是否重复
     *
     * @param username
     * @return
     */
    boolean checkUser(@Param("username") String username);


    /**
     * 用户名搜索用户
     *
     * @param username 用户名
     * @return
     */
    User loginUserByUserName(String username);

    /**
     * 邮箱搜索用户
     *
     * @param username 邮箱名
     * @return
     */
    User loginUserByEmail(String username);

    /**
     * 根据userid 查找用户
     *
     * @param userid
     * @return
     */
    User findOneById(int userid);

    /**
     * 根据id 查找用户所有信息
     *
     * @param userid
     * @return
     */
    User findOneAllById(int userid);

    /**
     * 根据邮箱查记录
     *
     * @param email
     * @return
     */
    Boolean checkEmailByEmail(String email);

    /**
     * 根据用户名查记录
     *
     * @param username
     * @return
     */
    Boolean checkUserNameByName(String username);

    /**
     * 修改密码
     *
     * @param userid
     * @param newPsw
     * @return
     */
    Boolean updatePswById(@Param("userid") int userid, @Param("newPsw") String newPsw, @Param("now") Timestamp now);

    /**
     * 修改邮箱
     *
     * @param user
     * @return
     */
    Boolean updateEmailById(User user);

    /**
     * 修改电话
     *
     * @param user
     * @return
     */
    Boolean updateTelephoneById(User user);

    /**
     * 根据用户id查询用户是否存在
     *
     * @return
     */
    Boolean checkUserExist(int userId);

    int checkUpdateCode(User user);
}
