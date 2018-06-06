package com.wx.springbootdemo.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.SpringbootDemoApplication;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
public class TestUserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUserInfoService.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void testfindByUsername() {

        UserInfo userInfo = userInfoService.selectByUsername("admin");
        LOGGER.info("===[{}]===", userInfo);

        UserInfo userInfo1 = userInfoService.selectByUsername("admin1");
        LOGGER.info("===[{}]===", userInfo1);

        Map param = Maps.newLinkedHashMap();
        param.put("start", 0);
        param.put("pageSize", 1000);
        List<Map> userInfoList = userInfoService.selectUserList(param);

        System.out.println(userInfoList);

    }

    @Test
    @Transactional
    public void testsaveUserInfo() throws Exception {

        Map param;
        for(int i=100;i <105; i++) {
            param = Maps.newLinkedHashMap();
            param.put("username", "user"+i);
            param.put("name", "用户"+i);
            param.put("password", "123456");
            int r = userInfoService.saveUserInfo(param);
            if(i==103) {
                throw new Exception();
            }
            System.out.println(r);
        }

    }

    @Test
    @Rollback(false)
    public void test() {
        List<Map> userList = Lists.newArrayList();
        Map userMap = Maps.newLinkedHashMap();
        userMap.put("uid", 82);
        userList.add(userMap);
        List<Integer> roleList = Lists.newArrayList();
        roleList.add(1);
        roleList.add(2);
        int r = userInfoService.saveUserSysrole(userList, roleList);
        System.out.println(r);
    }

}
