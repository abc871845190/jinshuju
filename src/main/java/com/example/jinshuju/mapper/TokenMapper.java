package com.example.jinshuju.mapper;

import com.example.jinshuju.pojo.UserRefreshToken;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;

@Mapper
public interface TokenMapper {

    /**
     * 创建refreshToken
     *
     * @param userRefreshToken
     */
    void insertRefreshToken(UserRefreshToken userRefreshToken);

    /**
     * 获取refreshToken
     *
     * @param Token
     * @return
     */
    UserRefreshToken getRefreshTokenByToken(String Token);

    /**
     * 根据userid来删除原来的refreshToken
     *
     * @param userid
     */
    void deleteRefreshTokenById(int userid);

    /**
     * 获取createtime
     *
     * @param userid
     * @return
     */
    Timestamp getCreateTimeById(int userid);
}
