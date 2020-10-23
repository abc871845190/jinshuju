package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.AsyncService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.RedisUtils;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.example.jinshuju.utils.TextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户控制类
 */
@RestController
//不能跳转的controller
//@Controller
@RequestMapping("/UserController")
@Slf4j
@Api("用户Api")
public class UserController {

    @Autowired(required = false)
    UserService userService;

    @Autowired(required = false)
    RedisUtils redisUtils;

    @Autowired(required = false)
    AsyncService asyncService;

    /**
     * 插入用户，即是新建用户
     *
     * @param user 用户对象 user属性必填为name，password，telephone，email
     * @return
     */
    @ApiOperation("插入用户，即是新建用户,注册")
    @PostMapping("/insertUser")
    public Result insertUser(@RequestBody User user) {
        log.info("user is :" + user.toString());
        return userService.registerUser(user);
    }

    /**
     * 登陆
     *
     * @return
     */
    @ApiOperation("登陆")
    @PostMapping("/loginUser")
    public Result loginUser(@RequestBody User user,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        return userService.loginUser(user, request, response);
    }

    @ApiOperation("获取用户信息")
    @GetMapping("/getUser")
    public Result getUser(User user,
                          HttpServletRequest request,
                          HttpServletResponse response) {
        return userService.getUser(user,request,response);
    }

    /**
     * 修改密码
     *
     * @param userid 用户id
     * @param user   用户对象
     * @return
     */
    @ApiOperation("修改密码")
    @PutMapping("/updatePsw/{userid}")
    public Result updatePsw(@PathVariable("userid") int userid, @RequestBody User user) {
        return ResultUtils.success();
    }

    /**
     * 修改用户信息
     *
     * @param userid 用户id
     * @param user   用户对象
     * @return
     */
    @ApiOperation("修改用户信息")
    @PutMapping("/updateData/{userid}")
    public Result updateData(@PathVariable("userid") int userid, @RequestBody User user) {
        return ResultUtils.success();
    }

    /**
     * 验证码邮箱
     *
     * @param email 邮箱地址
     * @return
     */
    @ApiOperation("验证码邮箱")
    @PostMapping("/checkEmail")
    public Result checkEmail(@RequestBody String email) {
        int key = TextUtils.RandomCode();
        //TODO:讲验证码存进redis数据库,并设置数据缓存时间5分钟
        //redisUtils.set("email_key",key,300);
        //异步去发送邮件
        asyncService.sendEmail("验证邮箱", "你本次验证码为：" + key + "，五分内有效。金数据小组出品", new String[]{email});
        return ResultUtils.success(String.valueOf(key));
    }
}
