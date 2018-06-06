package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.UserInfoExt;
import com.wx.springbootdemo.mapper.UserInfoExtMapper;
import com.wx.springbootdemo.service.UserInfoExtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class UserInfoExtServiceImpl implements UserInfoExtService{

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoExtServiceImpl.class);

    @Autowired
    private UserInfoExtMapper userInfoExtMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public UserInfoExt selectByUsername(String username) {
        return userInfoExtMapper.selectByUsername(username);
    }

    @Override
    public int saveUserInfoExt(UserInfoExt userInfoExt) {
        return userInfoExtMapper.insertSelective(userInfoExt);
    }

    @Override
    @Cacheable(value = "UserInfoExt")
    public UserInfoExt selectUserInfoExtById(Integer id) {
        LOGGER.info("===从数据库中获取id:[{}]对应的UserInfoExt===", id);
        return userInfoExtMapper.selectByPrimaryKey(id);
    }

    @Override
    @CacheEvict(value = "UserInfoExt")
    public void deleteUserInfoExtById(Integer id) {
        LOGGER.info("===从缓存中删除id:[{}]对应的UserInfoExt===", id);
        redisTemplate.delete("com.wx.springbootdemo.service.impl.UserInfoExtServiceImplselectUserInfoExtById1");
    }

    @Override
    public void test() {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("mykey4", "random1="+Math.random());
        LOGGER.info("===mykey4:[{}]===", valueOperations.get("mykey4"));
    }
}
