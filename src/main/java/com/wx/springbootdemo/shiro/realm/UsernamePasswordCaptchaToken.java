package com.wx.springbootdemo.shiro.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

public class UsernamePasswordCaptchaToken extends UsernamePasswordToken {

    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public UsernamePasswordCaptchaToken() {
    }

    public UsernamePasswordCaptchaToken(String username, String password, boolean rememberMe,
                                        String host, String captcha) {
        super(username, password, rememberMe, host);
        this.captcha = captcha;
    }

    @Override
    public String toString() {
        return "UsernamePasswordCaptchaToken{}";
    }
}
