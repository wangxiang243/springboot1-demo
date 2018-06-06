package com.wx.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.entity.UserInfoExt;
import com.wx.springbootdemo.service.UserInfoExtService;
import com.wx.springbootdemo.service.UserInfoService;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/userinfo")
public class UserInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

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

    @RequestMapping("selectUserInfoByUID")
    @RequiresPermissions("userinfo:list")
    public AjaxResult selectUserInfoByUID(Integer uid) {
        Map result = userInfoService.selectUserInfoByUID(uid);
        return AjaxResult.success(result, "用户查询成功");
    }

    @RequestMapping("selectUserInfoList")
    @RequiresPermissions("userinfo:list")
    public AjaxResult selectUserInfoList(@RequestParam Map param) {
        long timeout = SecurityUtils.getSubject().getSession().getTimeout();
        LOGGER.info("===[{}]===", timeout);
        Map result = userInfoService.selectUserInfoPageList(param);
        return AjaxResult.success(result, "用户查询成功");
    }

    @RequestMapping("saveUserInfo")
    @RequiresPermissions(value = {"userInfo:add", "userinfo:edit"}, logical = Logical.OR)
    public AjaxResult saveUserInfo(@RequestBody Map param) {
        if(param == null) {
            return AjaxResult.fail("用户信息不能为空!");
        }
        if(param.get("username") == null || StringUtils.isBlank(param.get("username").toString())) {
            return AjaxResult.fail("用户账号不能为空!");
        }
        if(param.get("name") == null || StringUtils.isBlank(param.get("name").toString())) {
            return AjaxResult.fail("用户姓名不能为空!");
        }
        if(param.get("password") == null || StringUtils.isBlank(param.get("password").toString())) {
            return AjaxResult.fail("密码不能为空!");
        }
        if(param.get("uid") == null || StringUtils.isBlank(param.get("uid").toString())) {
            userInfoService.saveUserInfo(param);
            return AjaxResult.success("用户添加成功");
        } else {
            userInfoService.updateUserInfo(param);
            return AjaxResult.success("用户编辑成功");
        }
    }

    @RequestMapping("deleteUserInfoByUID")
    @RequiresPermissions("userInfo:del")
    public AjaxResult deleteUserInfoByUID(@RequestParam Integer uid) {
        if(uid == null) {
            return AjaxResult.fail("用户id不能为空!");
        }
        userInfoService.deleteUserInfoByUID(uid);
        return AjaxResult.success("用户删除成功");
    }

    @RequestMapping("saveUserSysrole")
    @RequiresPermissions("userinfo:auth")
    public AjaxResult saveUserSysrole(@RequestBody String param) {
        LOGGER.info("===param:[{}]===", param);
        if(param == null || StringUtils.isBlank(param)) {
            LOGGER.error("用户角色授权信息不能为空!");
            return AjaxResult.fail("用户角色授权信息不能为空!");
        }
        Map<String, List> paramMap = JSON.parseObject(param, Map.class);
        List<Map> userList = paramMap.get("selectedRows");
        List<Integer> roleList = paramMap.get("selectedSysroleRows");
        if(userList == null || userList.size() == 0) {
            LOGGER.error("用户信息不能为空!");
            return AjaxResult.fail("用户信息不能为空!");
        }
        if(roleList == null || roleList.size() == 0) {
            LOGGER.error("角色信息不能为空!");
            return AjaxResult.fail("角色信息不能为空!");
        }
        userInfoService.saveUserSysrole(userList, roleList);
        return AjaxResult.success("用户角色授权成功");
    }


    @RequestMapping("test")
    public AjaxResult test() {
        userInfoExtService.test();
        return AjaxResult.success("用户信息redis缓存测试");
    }

    @RequestMapping("selectUserInfoExtById")
    public AjaxResult selectUserInfoExtById() {
        UserInfoExt userInfoExt = userInfoExtService.selectUserInfoExtById(1);
        UserInfoExt userInfoExt1 = userInfoExtService.selectUserInfoExtById(1);
        UserInfoExt userInfoExt2 = userInfoExtService.selectUserInfoExtById(11);
        return AjaxResult.success(userInfoExt, "获取userInfoExt成功");
    }

    @RequestMapping("deleteUserInfoExtById")
    public AjaxResult deleteUserInfoExtById() {
        userInfoExtService.deleteUserInfoExtById(1);
        return AjaxResult.success("删除userInfoExt成功");
    }

}
