package com.wx.springbootdemo.controller;

import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.entity.UserInfoExt;
import com.wx.springbootdemo.service.UserInfoExtService;
import com.wx.springbootdemo.service.UserInfoService;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/userinfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserInfoExtService userInfoExtService;

    @RequestMapping("/getCurrentUser")
    public AjaxResult getCurrentUser(){
        String username = ((UserInfo)SecurityUtils.getSubject().getPrincipal()).getUsername();
//        String username = "admin";
        UserInfoExt userInfoExt = userInfoExtService.selectByUsername(username);
        Map result = Maps.newLinkedHashMap();
        result.put("uid", userInfoExt.getUid());
        result.put("name", userInfoExt.getName());
        result.put("avatar", userInfoExt.getAvatar());
        result.put("notifyCount", 10);
        return AjaxResult.success(result);
    }

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
