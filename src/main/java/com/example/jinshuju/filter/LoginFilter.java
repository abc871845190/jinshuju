package com.example.jinshuju.filter;

import com.alibaba.fastjson.JSON;
import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.UserService;
import com.example.jinshuju.utils.ResultUtils.ResultEnum;
import com.example.jinshuju.utils.ResultUtils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登陆拦截
 */
@Slf4j
public class LoginFilter extends HandlerInterceptorAdapter {

    @Autowired(required = false)
    UserService userService;

    /**
     * 处理请求之前该干的事
     *
     * @param request
     * @param response
     * @param handler
     * @return true跳转到下一个方法 false不跳转
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //开始拦截登陆,检测是否登陆
        User user = userService.checkUserLogin(request, response);
        if (user != null) {
            //已经登陆了
            log.info("已经登陆了");
            return true;
        }else{
            String jsonStr = JSON.toJSONString(ResultUtils.fail(ResultEnum.USER_NOT_LOGIN.getCode(), ResultEnum.USER_NOT_LOGIN.getMsg()));
            returnJson(response,jsonStr);
            return false;
        }
    }

    /**
     * 返回json
     * @param response
     * @param jsonStr
     */
    private void returnJson(HttpServletResponse response, String jsonStr) throws IOException {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(jsonStr);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    /**
     * 处理请求之后，在视图渲染之前该干的事
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 整个请求完全结束后，渲染视图后调用
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
