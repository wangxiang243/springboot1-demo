package com.wx.springbootdemo.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Locale;

public class UserLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        if (isPostOnlyLogout()) {

            // check if the current request's method is a POST, if not redirect
            if (!WebUtils.toHttp(request).getMethod().toUpperCase(Locale.ENGLISH).equals("POST")) {
                return onLogoutRequestNotAPost(request, response);
            }
        }
        subject.logout();
        //前后端分离时使用ajax请求登录成功后直接返回json数据
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(JSON.toJSON(AjaxResult.success("成功退出")));
        printWriter.flush();
        printWriter.close();
        return false;
    }
}
