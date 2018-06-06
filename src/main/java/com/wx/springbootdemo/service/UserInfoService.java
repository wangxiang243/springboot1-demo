package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.UserInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

public interface UserInfoService {

    UserInfo selectByUsername(String username);

    Map selectUserInfoByUID(Integer uid);
    List<Map> selectUserList(Map param);

    Map selectUserInfoPageList(Map param);

    int saveUserInfo(Map param);
    int updateUserInfo(Map param);

    int saveUserSysrole(List<Map> userList, List<Integer> roleIdList);

    int deleteUserInfoByUID(Integer uid);



}
