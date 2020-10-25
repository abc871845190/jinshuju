package com.example.jinshuju.filter;

import com.example.jinshuju.pojo.User;
import com.example.jinshuju.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        }
        log.info("还没登陆呢");
        //重定向
        //request.getRequestDispatcher("/RootController/returnMsg/please login").forward(request,response);
        return false;
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
