package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    UserInfo selectByUsername(String username);

    List<UserInfo> selectUserList();
}
