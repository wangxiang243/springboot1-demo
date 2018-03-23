package com.wx.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class TestController {

    @RequestMapping(value="/getSessionId")
    public String getSessionId(HttpServletRequest request){

        Object o = request.getSession().getAttribute("springboot");
        if(o == null){
            o = "spring boot 牛逼了!!!有端口"+request.getLocalPort()+"生成";
            request.getSession().setAttribute("springboot", o);
        }

        return "端口=" + request.getLocalPort() +  " sessionId=" + request.getSession().getId() +"<br/>"+o;
    }

}
