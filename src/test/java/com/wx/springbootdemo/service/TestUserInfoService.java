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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDemoApplication.class)
public class TestUserInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestUserInfoService.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

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

    @Test
    public void testRedisOps() {

        //set操作
        Set<String> stringSet = new HashSet<>();
        stringSet.add("set1");
        stringSet.add("set2");
        stringSet.add("set3");
        redisTemplate.opsForSet().add("stringSet", stringSet);
        Set<String> resultStringSet = redisTemplate.opsForSet().members("stringSet");
        System.out.println(resultStringSet);

        //map操作
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("k1", "v1");
        stringMap.put("k2", "v2");
        stringMap.put("k3", "v3");
        redisTemplate.opsForHash().putAll("stringMap", stringMap);
        Set<String> stringMapkeys = redisTemplate.opsForHash().keys("stringMap");
        System.out.println("stringMapkeys:"+stringMapkeys);
        Map resultMap = redisTemplate.opsForHash().entries("stringMap");
        System.out.println("resultMap:"+resultMap);
        List<String> valuelist = redisTemplate.opsForHash().values("stringMap");
        System.out.println("valuelist:"+valuelist);
        String v1 = (String) redisTemplate.opsForHash().get("stringMap", "k1");
        System.out.println("v1:"+v1);

        //list操作
        List<String> list1 = new ArrayList<>();
        list1.add("l11");
        list1.add("l12");
        list1.add("l13");
        List<String> list2 = new ArrayList<>();
        list2.add("l21");
        list2.add("l22");
        list2.add("l23");
        redisTemplate.opsForList().leftPush("list1", list1);
        redisTemplate.opsForList().rightPush("list2", list2);
        List<String> resultlist1 = (List<String>) redisTemplate.opsForList().leftPop("list1");
        List<String> resultlist2 = (List<String>) redisTemplate.opsForList().rightPop("list2");
        System.out.println("resultlist1:"+resultlist1);
        System.out.println("resultlist2:"+resultlist2);

    }

    @Test
    public void testredisops1() {

        //string操作
        redisTemplate.opsForValue().set("k1", "v1");
        String sv1 = (String) redisTemplate.opsForValue().get("k1");
        System.out.println("k1:"+sv1);

        List<UserInfo> userInfoList = new ArrayList<>();
        UserInfo userInfo1 = new UserInfo("u1", "p1", "s1", "n1", Byte.valueOf("1"));
        UserInfo userInfo2 = new UserInfo("u2", "p2", "s2", "n2", Byte.valueOf("2"));
        userInfoList.add(userInfo1);
        userInfoList.add(userInfo2);
        redisTemplate.opsForValue().set("userInfoList", userInfoList);
        List<UserInfo> userInfos = (List<UserInfo>) redisTemplate.opsForValue().get("userInfoList");
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo);
        }

    }

}
