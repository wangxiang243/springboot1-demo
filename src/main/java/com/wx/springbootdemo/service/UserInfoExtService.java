package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.UserInfoExt;

public interface UserInfoExtService {

    UserInfoExt selectByUsername(String username);

    int saveUserInfoExt(UserInfoExt userInfoExt);

    UserInfoExt selectUserInfoExtById(Integer id);

    void deleteUserInfoExtById(Integer id);

    void test();

}
