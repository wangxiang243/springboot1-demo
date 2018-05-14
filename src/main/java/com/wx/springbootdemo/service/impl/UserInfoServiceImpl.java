package com.wx.springbootdemo.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.entity.UserInfoExt;
import com.wx.springbootdemo.mapper.UserInfoExtMapper;
import com.wx.springbootdemo.mapper.UserInfoMapper;
import com.wx.springbootdemo.service.UserInfoService;
import com.wx.springbootdemo.util.PageView;
import com.wx.springbootdemo.util.UserUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoExtMapper userInfoExtMapper;

    @Override
    public UserInfo selectByUsername(String username) {
        return userInfoMapper.selectByUsername(username);
    }

    @Override
    public List<Map> selectUserList(Map param) {
        return userInfoMapper.selectUserList(param);
    }

    @Override
    public Map selectUserInfoPageList(Map param) {

        int start = PageView.getStart(param);
        param.put("start", start);
        param.put("pageSize", PageView.getPageSize(param));
        int count = userInfoMapper.selectUserCount(param);
        List<Map> userList = userInfoMapper.selectUserList(param);
        Map result = PageView.getPageView(param, count, userList);
        return result;

    }

    @Override
    public int saveUserInfo(Map param) {
        String username = param.get("username").toString();
        String name = param.get("name").toString();
        String sourcePassword = param.get("password").toString();
        String salt = new Md5Hash("wx" + username).toString();
        String password = UserUtils.getPassword(sourcePassword, salt);
        byte state = Byte.parseByte("0");
        Date createTime = new Date();

        UserInfo userInfo = new UserInfo(username, password, salt, name, state);
        int r1 = userInfoMapper.insertSelective(userInfo);
        UserInfoExt userInfoExt = new UserInfoExt(userInfo.getUid(), username, name, "", state, createTime, createTime);
        int r2 = userInfoExtMapper.insertSelective(userInfoExt);
        return r1+r2;
    }

    @Override
    public int saveUserSysrole(List<Map> userList, List<Integer> roleIdList) {

        Integer uid = Integer.valueOf(userList.get(0).get("uid").toString());
        userInfoMapper.deleteUserroleByUID(uid);
        List<Map> userroleList = Lists.newArrayList();
        Map userrole;
        for(Integer roleId : roleIdList) {
            userrole = Maps.newLinkedHashMap();
            userrole.put("uid", uid);
            userrole.put("roleId", roleId);
            userroleList.add(userrole);
        }
        int r = userInfoMapper.saveUserroleList(userroleList);
        return r;
    }
}
