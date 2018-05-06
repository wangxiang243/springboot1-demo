package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.UserInfoExt;

public interface UserInfoExtService {

    UserInfoExt selectByUsername(String username);

}
