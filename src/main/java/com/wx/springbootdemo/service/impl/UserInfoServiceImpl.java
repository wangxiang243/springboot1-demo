package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.mapper.UserInfoMapper;
import com.wx.springbootdemo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectByUsername(String username) {
        return userInfoMapper.selectByUsername(username);
    }

    @Override
    public List<UserInfo> selectUserList() {
        return userInfoMapper.selectUserList();
    }

}
