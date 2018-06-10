package com.wx.springbootdemo.config;

import com.google.common.collect.Maps;
import com.wx.springbootdemo.interceptor.spring.DataPermissionInterceptor;
import com.wx.springbootdemo.service.SysDataRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebApplicationConfig extends WebMvcConfigurerAdapter {

    @Bean
    public DataPermissionInterceptor dataPermissionInterceptor() {
        return new DataPermissionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.dataPermissionInterceptor()).excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

}
