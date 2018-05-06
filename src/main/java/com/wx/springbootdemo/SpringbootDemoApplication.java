package com.wx.springbootdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@MapperScan("com.wx.springbootdemo.mapper")
public class SpringbootDemoApplication extends WebMvcConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringbootDemoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemoApplication.class, args);
        LOGGER.info("===项目启动成功===");
    }
}
