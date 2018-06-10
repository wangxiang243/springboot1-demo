package com.wx.springbootdemo.interceptor.spring;

import com.google.common.collect.Maps;
import com.wx.springbootdemo.constants.GlobalConstants;
import com.wx.springbootdemo.entity.SysDataRule;
import com.wx.springbootdemo.service.SysDataRuleService;
import com.wx.springbootdemo.util.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class DataPermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private SysDataRuleService sysDataRuleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestPath = request.getRequestURI().substring(request.getContextPath().length()+1);
        Map param = Maps.newLinkedHashMap();
        param.put("uid", UserUtils.getCurrentUser().getUid());
        param.put("url", requestPath);
        List<SysDataRule> sysDataRuleList = sysDataRuleService.selectSysDataRuleByUidAndUrl(param);
        request.setAttribute(GlobalConstants.SYS_DATA_RULE_LIST, sysDataRuleList);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
