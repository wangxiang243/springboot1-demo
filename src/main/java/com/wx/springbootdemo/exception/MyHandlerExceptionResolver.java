package com.wx.springbootdemo.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        ModelAndView mv = new ModelAndView();
        FastJsonJsonView fastJsonJsonView = new FastJsonJsonView();
//        fastJsonJsonView.setBeanName("fastJsonJsonView");
        Map attributes;
        if(ex instanceof UnauthorizedException) {
            attributes = AjaxResult.failMap("当前用户无权限");
        } else if(ex instanceof UnauthenticatedException) {
            attributes = AjaxResult.failMap("当前用户未登录");
        } else {
            attributes = AjaxResult.failMap(ex.getMessage());
        }
        fastJsonJsonView.setAttributesMap(attributes);
        mv.setView(fastJsonJsonView);
        return mv;

    }

}
