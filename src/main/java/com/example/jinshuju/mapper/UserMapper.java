package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
