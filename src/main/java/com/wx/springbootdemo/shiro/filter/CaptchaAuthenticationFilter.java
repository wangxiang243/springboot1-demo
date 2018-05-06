package com.wx.springbootdemo.shiro.filter;

import com.alibaba.fastjson.JSON;
import com.wx.springbootdemo.shiro.realm.UsernamePasswordCaptchaToken;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class CaptchaAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaAuthenticationFilter.class);

    private static final String CAPTCHA = "captcha";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        StringBuilder sb = new StringBuilder();
        try(BufferedReader reader = request.getReader();) {
            char[]buff = new char[64];
            int len;
            while((len = reader.read(buff)) != -1) {
                sb.append(buff,0, len);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        LOGGER.info("===[{}]===", sb.toString());

        Map<String, String> loginMap = JSON.parseObject(sb.toString(), Map.class);
//        String username = getUsername(request);
//        String password = getPassword(request);
        String username = loginMap.get("username");
        String password = loginMap.get("password");
        boolean isRemeberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = WebUtils.getCleanParam(request, CAPTCHA);
        UsernamePasswordCaptchaToken usernamePasswordCaptchaToken = new UsernamePasswordCaptchaToken(username, password, isRemeberMe, host, captcha);
        return usernamePasswordCaptchaToken;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception{

        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) createToken(request, response);
        if (token == null) {
            String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken " +
                    "must be created in order to execute a login attempt.";
            throw new IllegalStateException(msg);
        }
        String captcha = "abcde"; //todo
        String currentCaptcha = captcha;//token.getCaptcha();
        if(StringUtils.isBlank(captcha) || !captcha.equals(currentCaptcha)) {
            LOGGER.error("===正确的验证码为[{}], 实际为[{}]===", captcha, currentCaptcha);
            request.setAttribute(getFailureKeyAttribute(), "验证码不正确");
            return true;
        }
        try {
            Subject subject = getSubject(request, response);
            subject.login(token);
            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            return onLoginFailure(token, e, request, response);
        }

    }


    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {

        boolean isAjaxRequest = AjaxResult.isAjaxRequest(request);
        //前后端分离时使用ajax请求登录成功后直接返回json数据
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = httpServletResponse.getWriter();
        printWriter.println(JSON.toJSON(AjaxResult.success("登录成功")));
        printWriter.flush();
        printWriter.close();
        return false;
//        return super.onLoginSuccess(token, subject, request, response);
    }
}
