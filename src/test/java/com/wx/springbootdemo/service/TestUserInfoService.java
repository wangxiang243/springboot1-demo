package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUserInfoService.class);

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void testfindByUsername() {

        UserInfo userInfo = userInfoService.selectByUsername("admin");
        LOGGER.info("===[{}]===", userInfo);

        UserInfo userInfo1 = userInfoService.selectByUsername("admin1");
        LOGGER.info("===[{}]===", userInfo1);

        List<UserInfo> userInfoList = userInfoService.selectUserList();
        System.out.println(userInfoList);

    }

}
