package com.wx.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.entity.UserInfoExt;
import com.wx.springbootdemo.service.UserInfoExtService;
import com.wx.springbootdemo.service.UserInfoService;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @RequestMapping("selectUserInfoList")
    @RequiresPermissions("userinfo:list")
    public AjaxResult selectUserInfoList(@RequestParam Map param) {
        long timeout = SecurityUtils.getSubject().getSession().getTimeout();
        LOGGER.info("===[{}]===", timeout);
        Map result = userInfoService.selectUserInfoPageList(param);
        return AjaxResult.success(result, "用户查询成功");
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

    @RequestMapping("saveUserSysrole")
    @RequiresPermissions("userinfo:auth")
    @Transactional
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

}
