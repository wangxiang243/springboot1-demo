package com.wx.springbootdemo.controller;

import com.wx.springbootdemo.shiro.filter.CaptchaAuthenticationFilter;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/login")
    public AjaxResult login(HttpServletRequest request, Map map) {

        LOGGER.info("===跳转至登录页===");

        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
        String exception = (String) request.getAttribute(CaptchaAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                LOGGER.error("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                LOGGER.error("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                LOGGER.error("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else if("org.apache.shiro.authc.AuthenticationException".equals(exception)) {
                LOGGER.error("用户名或密码错误:[{}]", exception);
                msg = "用户名或密码错误";
            } else {
                msg = "else >> "+exception;
                LOGGER.error("else -- >" + exception);
            }
        } else {
            //判断是否已登录
            if(SecurityUtils.getSubject().isAuthenticated()) {
                //重复登录直接返回成功
                LOGGER.info("===当前状态为已登录===");
                return AjaxResult.success("已登录");
            } else {
                //未登录直接访问资源时，客户端直接跳转至登录页


                LOGGER.error("===当前状态为未登录，请先登录===");
                return AjaxResult.fail("/user/login", msg);
            }
        }

        if(AjaxResult.isAjaxRequest(request)) {
            LOGGER.info("===当前请求是ajax===");
            return AjaxResult.fail(msg);
        }
        LOGGER.error("===登录异常:[{}], [{}]===", exception, msg);
        map.put("msg", msg);
        return AjaxResult.fail(msg);

    }

    @RequestMapping({"/","/success"})
    public AjaxResult index() {
        return AjaxResult.success("登录成功");
    }

/*    @RequestMapping("/noPermission")
    public AjaxUtils noPermission() {
        LOGGER.error("===没有当前请求资源的操作权限===");
        return AjaxUtils.fail("没有当前资源操作权限");
    }*/

}
