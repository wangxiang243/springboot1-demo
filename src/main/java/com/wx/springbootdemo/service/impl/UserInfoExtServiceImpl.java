package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.UserInfoExt;
import com.wx.springbootdemo.mapper.UserInfoExtMapper;
import com.wx.springbootdemo.service.UserInfoExtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoExtServiceImpl implements UserInfoExtService{

    @Autowired
    private UserInfoExtMapper userInfoExtMapper;

    @Override
    public UserInfoExt selectByUsername(String username) {
        return userInfoExtMapper.selectByUsername(username);
    }

    @Override
    public int saveUserInfoExt(UserInfoExt userInfoExt) {
        return userInfoExtMapper.insertSelective(userInfoExt);
    }

}
