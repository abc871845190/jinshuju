package com.example.jinshuju.utils;

import com.example.jinshuju.pojo.User;
import io.jsonwebtoken.Claims;

import java.util.HashMap;
import java.util.Map;

/**
 * token解析工具类
 */
public class ClaimsUtils {

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String USERIMG = "userimg";
    public static final String USERMAIL = "usermail";
    public static final String USERTELEPHONE = "usertelephone";

    /**
     * 用户转化为map
     *
     * @param user
     * @return
     */
    public static Map<String, Object> UserToClaims(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(ID, user.getUserId());
        claims.put(USERNAME, user.getUserName());
        if (user.getUserImg() == "" || user.getUserImg() == null) {
            claims.put(USERIMG, "localhost:8080/img/default_img.jpg");
        } else {
            claims.put(USERIMG, user.getUserImg());
        }
        claims.put(USERMAIL, user.getUserEmail());
        claims.put(USERTELEPHONE,user.getUserTelephone());
        return claims;
    }

    /**
     * map转化为用户
     *
     * @param claims
     * @return
     */
    public static User ClaimsToUser(Claims claims) {
        User user = new User();
        user.setUserName((String) claims.get(USERNAME));
        user.setUserId((int) claims.get(ID));
        user.setUserImg((String) claims.get(USERIMG));
        user.setUserEmail((String) claims.get(USERMAIL));
        user.setUserTelephone((String) claims.get(USERTELEPHONE));
        return user;
    }
}
