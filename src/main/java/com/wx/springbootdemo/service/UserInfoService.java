package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    UserInfo selectByUsername(String username);

    List<Map> selectUserList(Map param);

    Map selectUserInfoPageList(Map param);

    int saveUserInfo(Map param);

    int saveUserSysrole(List<Map> userList, List<Integer> roleIdList);
}
