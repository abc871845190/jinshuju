package com.example.jinshuju.controller;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.AsyncService;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.RedisUtils;
import com.example.jinshuju.utils.ResultUtils.Result;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import com.example.jinshuju.utils.TextUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户控制类
 */
@RestController
//不能跳转的controller
//@Controller
@RequestMapping("/UserController")
@Slf4j
@Api("用户Api接口")
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
    @ApiOperation(value = "插入用户，即是新建用户,注册", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PostMapping("/User")
    public Result insertUser(@RequestBody User user) {
        log.info("user is :" + user.toString());
        return userService.registerUser(user);
    }

    /**
     * 登陆
     *
     * @return
     */
    @ApiOperation(value = "登陆", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PostMapping("/loginUser")
    public Result loginUser(@RequestBody User user,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE");
        return userService.loginUser(user, request, response);
    }

    @ApiOperation(value = "获取用户信息", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/User")
    public Result getUser(HttpServletRequest request,HttpServletResponse response) {
        User user = userService.checkUserLogin(request,response);
        if (user == null){
            return ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(),ResultEnum.USER_NOT_LOGIN.getMsg());
        }
        return userService.getUserInfo(user);
    }

    /**
     * 修改密码，用json存用户id和密码
     *
     * @return
     */
    @ApiOperation(value = "修改密码", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "更新密码成功"),
            @ApiResponse(code = 2, message = "更新密码失败"),
            @ApiResponse(code = 2, message = "原密码和用户密码不一样"),
            @ApiResponse(code = 2, message = "用户id找不到密码")

    })
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "userid", value = "用户id", required = true, dataType = "int", example = "1"),
            @ApiImplicitParam(paramType = "query", name = "oriPsw", value = "原密码", required = true, dataType = "String", example = "123456"),
            @ApiImplicitParam(paramType = "query", name = "newPsw", value = "新密码", required = true, dataType = "String", example = "1234567")
    })
    @PatchMapping("/updatePsw")
    public Result updatePsw(@RequestBody Map<String, Object> map) {
        int userid = (int) map.get("userid");
        String originPsw = (String) map.get("oriPsw");
        String newPsw = (String) map.get("newPsw");
        //log.info("id = " + userid + "  oriPsw = " + originPsw + "  newPsw = " + newPsw);
        return userService.updatePsw(userid, originPsw, newPsw);
    }

    /**
     * 修改邮箱
     *
     * @param user 用户对象
     * @return
     */
    @ApiOperation(value = "修改邮箱", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateEmail")
    public Result updateEmail(@RequestBody User user) {
        return userService.updateEmail(user);
    }

    @ApiOperation(value = "修改电话", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @PatchMapping("/updateTelephone")
    public Result updateTelephone(@RequestBody User user) {
        return userService.updateTelephone(user);
    }

    /**
     * 验证码邮箱
     *
     * @param email 邮箱地址
     * @return
     */
    @ApiOperation("验证码邮箱")
    @PutMapping("/bindEmail")
    public Result bindEmail(@RequestBody String email) {
        int key = TextUtils.RandomCode();
        //TODO:讲验证码存进redis数据库,并设置数据缓存时间5分钟
        //redisUtils.set("email_key",key,300);
        //异步去发送邮件
        asyncService.sendEmail("验证邮箱", "你本次验证码为：" + key + "，五分内有效。金数据小组出品", new String[]{email});
        return ResultUtils.success(String.valueOf(key));
    }

    @ApiOperation(value = "检查邮箱是否被注册，同用户名", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "邮箱存在"),
            @ApiResponse(code = 2, message = "邮箱不存在")
    })
    @GetMapping("/checkEmail")
    public Result checkEmail(@RequestParam("email") String email) {
        return userService.checkEmail(email);
    }

    @ApiOperation(value = "检查用户名是否被注册", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "用户名存在"),
            @ApiResponse(code = 2, message = "用户名不存在")
    })
    @GetMapping("/checkUserName")
    public Result checkUserName(@RequestParam("username") String username) {
        return userService.checkUserName(username);
    }

    @ApiOperation(value = "注销", response = Result.class)
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 2, message = "失败")
    })
    @GetMapping("/logout")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        return userService.logout(request, response);
    }
}
