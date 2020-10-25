package com.example.jinshuju.service.impl;

import com.example.jinshuju.mapper.TokenMapper;
import com.example.jinshuju.mapper.UserMapper;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.pojo.UserRefreshToken;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.*;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

/**
 * 用户service处理事务实现类
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    //当我们在使用@Autowired注解的时候，默认required=true,表示注入的时候bean必须存在，否则注入失败。
    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    TokenMapper tokenMapper;

    @Autowired(required = false)
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired(required = false)
    RedisUtils redisUtils;

    @Override
    public Result registerUser(User user) {
        //检查是否为空
        if (TextUtils.isEmpty(user.getUserName())) {
            return ResultUtils.fail("用户名为空");
        }
        if (TextUtils.isEmpty(user.getUserPassword())) {
            return ResultUtils.fail("密码为空");
        }
        if (TextUtils.isEmpty(user.getUserEmail())) {
            return ResultUtils.fail("邮箱为空");
        }
        if (TextUtils.isEmpty(user.getUserTelephone())) {
            return ResultUtils.fail("电话为空");
        }
        //检查用户是否存在
        if (userMapper.checkUser(user.getUserName())) {
            return ResultUtils.fail("用户名重复");
        }
        //相关用户属性设置
        user.setUserCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setUserUpdateTime(new Timestamp(System.currentTimeMillis()));
        user.setUserEmailStatus(0);
        //用户密码加密
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        //存入数据库
        userMapper.insertUser(user);
        return ResultUtils.success();
    }

    @Override
    public Result loginUser(User user,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        //判空
        String username = user.getUserName();
        if (TextUtils.isEmpty(username)) {
            return ResultUtils.fail("用户名为空");
        }
        String password = user.getUserPassword();
        if (TextUtils.isEmpty(password)) {
            return ResultUtils.fail("密码为空");
        }
        User foundUser = userMapper.loginUserByUserName(username);
        //判断用户名是否能找到
        if (foundUser == null) {
            //判断邮箱是否能找到
            foundUser = userMapper.loginUserByEmail(username);
        }
        //返回不存在结果
        if (foundUser == null) {
            return ResultUtils.fail("用户名不存在");
        }
        //比较密码
        if (bCryptPasswordEncoder.matches(password, foundUser.getUserPassword())) {
            log.info("login successfully,createRefreshToken ing---");
            createRefreshToken(response, foundUser, new Timestamp(System.currentTimeMillis()));
            return ResultUtils.success("登陆成功");
        } else {
            return ResultUtils.fail("用户密码错误");
        }
    }

    /**
     * 创建refreshToken
     *
     * @param response
     * @param foundUser
     * @param lastTime
     * @return tokenkey
     */
    private String createRefreshToken(HttpServletResponse response, User foundUser, Timestamp lastTime) {
        //删除一下原来的refreshToken
        int userid = foundUser.getUserId();
        //log.info("userid is   ==>   " + String.valueOf(userid));
        tokenMapper.deleteRefreshTokenById(userid);
        //生成token,自定1小时过期
        String token = JwtUtils.createJWT(ClaimsUtils.UserToClaims(foundUser), Constants.TimeValue.ONE_HOUR * 2 * 1000);
        //log.info("create token   ==>   " + token);
        //生成md5版本的token
        String tokenKey = DigestUtils.md5DigestAsHex(token.getBytes());
        //log.info("create md5 token   ==>   " + tokenKey);
        //保存token到redis
        redisUtils.set(Constants.User.KEY_TOKEN + tokenKey, token, Constants.TimeValue.ONE_HOUR * 2);
        //把tokenKey写到cookie里面
        CookiesUtils.setCookies(response, Constants.User.COOKIES_TOKEN, tokenKey, "/");
        //生成refreshToken
        String refreshToken = JwtUtils.createRefreshJWT(String.valueOf(foundUser.getUserId()), Constants.TimeValue.ONE_WEEK * 1000);
        //log.info("create refreshToken is   ==>   "+refreshToken);
        UserRefreshToken userRefreshToken = new UserRefreshToken();
        userRefreshToken.setUserId(foundUser.getUserId());
        userRefreshToken.setRefreshToken(refreshToken);
        userRefreshToken.setToken(token);
        userRefreshToken.setCreateTime(lastTime);
        userRefreshToken.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //插入到数据库里面
        tokenMapper.insertRefreshToken(userRefreshToken);
        return tokenKey;
    }

    @Override
    public User checkUserLogin(HttpServletRequest request, HttpServletResponse response) {
        //拿cookies的md5token
        String tokenKey = CookiesUtils.getCookieValue(request, Constants.User.COOKIES_TOKEN);
        //log.info("tokenKey   ==>   " + tokenKey);
        //把cookies的md5token在redis里面拿数据
        User realuser = parseByToken(tokenKey);
        if (realuser == null) {
            log.info("realuser is null");
            //报错证明token已经过期或者没有该token
            String token = (String) redisUtils.get(Constants.User.KEY_TOKEN + tokenKey);
            //log.info("token is   ==>   " + token);
            UserRefreshToken userRefreshToken = tokenMapper.getRefreshTokenByToken(token);
            //到数据库拿refreshToken看是否存在，
            if (userRefreshToken == null) {
                //TODO:提示用户登录
                log.info("userRefreshToken is null");
                return null;
            }
            //存在即还没过期，
            //不存在就是没登录，
            try {
                //有效，就创建新的refreshToken，和新的Token
                //解析refreshToken，
                String refreshToken = userRefreshToken.getRefreshToken();
                //log.info("refreshToken is   ==>   " + refreshToken);
                Claims claims = JwtUtils.parseJWT(refreshToken);
                String userid = claims.getId();
                //log.info("refreshToken userid   ==>   " + userid);
                User user = userMapper.findOneById(Integer.parseInt(userid));
                //获取之前的createdtime
                Timestamp lasttime = tokenMapper.getCreateTimeById(Integer.parseInt(userid));
                //删除原来的refreshToken
                tokenMapper.deleteRefreshTokenById(Integer.parseInt(userid));
                //创建新的refreshToken
                String newTokenKey = createRefreshToken(response, user, lasttime);
                //log.info("new TokenKey is   ==>   " + newTokenKey);
                //返回token
                return parseByToken(newTokenKey);
            } catch (Exception e1) {
                //报错，即refreshToken过期
                //TODO:提示用户登陆
                e1.printStackTrace();
                log.info("refreshToken is expiration");
                return null;
            }
        }
        return realuser;
    }

    @Override
    public Result getUserInfo(int userid) {
        User user1 = userMapper.findOneById(userid);
        if (user1 == null) {
            return ResultUtils.fail("用户不存在");
        }
        return ResultUtils.success().setData(user1);
    }

    @Override
    public Result checkEmail(String email) {
        return userMapper.checkEmailByEmail(email) == true ? ResultUtils.success("邮箱存在") : ResultUtils.fail("邮箱不存在");
    }

    @Override
    public Result checkUserName(String username) {
        return userMapper.checkUserNameByName(username) == true ? ResultUtils.success("用户名存在") : ResultUtils.fail("用户名不存在");
    }

    @Override
    public Result updatePsw(int userid, String originPsw, String newPsw) {
        //判空
        if (userid == 0) {
            return ResultUtils.fail("用户id为空");
        }
        if (TextUtils.isEmpty(originPsw)) {
            return ResultUtils.fail("原密码为空");
        }
        if (TextUtils.isEmpty(newPsw)) {
            return ResultUtils.fail("新密码为空");
        }
        if (originPsw.equals(newPsw)) {
            return ResultUtils.fail("原密码和新密码一样");
        }
        //拿用户数据
        User foundUser = userMapper.findOneAllById(userid);
        if (foundUser != null) {
            //判原密码
            Boolean equalsPsw = checkPsw(foundUser.getUserPassword(), originPsw);
            //log.info("equalPsw is "+equalsPsw);
            if (equalsPsw) {
                //原密码一致，加密，更新密码
                if (userMapper.updatePswById(userid, bCryptPasswordEncoder.encode(newPsw), new Timestamp(System.currentTimeMillis()))) {
                    return ResultUtils.success("更新密码成功");
                } else {
                    return ResultUtils.fail("更新密码失败");
                }
            } else {
                return ResultUtils.fail("原密码和用户密码不一样");
            }
        }
        return ResultUtils.fail("用户id找不到密码");
    }

    @Override
    public boolean checkPsw(String userPsw, String originPsw) {
        //log.info("userPsw is   ==>   "+userPsw);
        if (bCryptPasswordEncoder.matches(originPsw, userPsw)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Result checkUpdateCode(User user) {
        int code = userMapper.checkUpdateCode(user);
        log.info("code is   ==>   " + String.valueOf(code));
        return ResultUtils.success("成功。", code);
    }

    private User parseByToken(String tokenKey) {
        String token = (String) redisUtils.get(Constants.User.KEY_TOKEN + tokenKey);
        //log.info("redis-token   ==>   " + token);
        if (token != null) {
            //有token，解析token
            try {
                Claims claims = JwtUtils.parseJWT(token);
                User user = ClaimsUtils.ClaimsToUser(claims);
                log.info("redis-token is alive");
                return user;
            } catch (Exception e) {
                //token过期了
                log.info("redis-token is expiration");
                return null;
            }
        }
        log.info("redis-token is null");
        return null;
    }
}