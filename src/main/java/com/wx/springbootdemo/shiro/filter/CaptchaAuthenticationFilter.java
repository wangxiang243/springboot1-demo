package com.wx.springbootdemo.shiro.filter;

import com.wx.springbootdemo.shiro.realm.UsernamePasswordCaptchaToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CaptchaAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaAuthenticationFilter.class);

    private static final String CAPTCHA = "captcha";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {

        String username = getUsername(request);
        String password = getPassword(request);
        boolean isRemeberMe = isRememberMe(request);
        String host = getHost(request);
        String captcha = WebUtils.getCleanParam(request, CAPTCHA);
        UsernamePasswordCaptchaToken usernamePasswordCaptchaToken = new UsernamePasswordCaptchaToken(username, password.toCharArray(), isRemeberMe, host, captcha);
        return usernamePasswordCaptchaToken;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) createToken(request, response);
        String currentCaptcha = token.getCaptcha();
        String captcha = "abcde"; //todo
        if(StringUtils.isBlank(captcha) || !currentCaptcha.equals(captcha)) {
            LOGGER.error("===正确的验证码为[{}], 实际为[{}]===", captcha, currentCaptcha);
            request.setAttribute(getFailureKeyAttribute(), "验证码不正确");
            return true;
        }
        return super.onAccessDenied(request, response);

    }

}
