package com.wx.springbootdemo.controller;

import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("selectUserList")
    @RequiresPermissions("userInfo:view")
    public List<UserInfo> selectUserList() {
        return userInfoService.selectUserList();
    }

    @RequestMapping("userAdd")
    @RequiresPermissions("userInfo:add")
    public String userAdd() {
        return "添加用户";
    }

    @RequestMapping("userDel")
    @RequiresPermissions("userInfo:del")
    public String userDel() {
        return "删除用户";
    }

}
