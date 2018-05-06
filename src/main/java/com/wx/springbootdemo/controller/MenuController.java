package com.wx.springbootdemo.controller;

import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.service.SysPermissionService;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/menu/")
public class MenuController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

    @Autowired
    private SysPermissionService sysPermissionService;

    @RequestMapping("getMenuData")
    public AjaxResult getMenuData() {

        /*List<Map> menuList = Lists.newArrayList();

        Map m1 = Maps.newLinkedHashMap();
        menuList.add(m1);
        m1.put("name", "用户管理");
        m1.put("icon", "dashboard");
        m1.put("path", "dashboard");
        List<Map> childrenList1 = Lists.newArrayList();
        m1.put("children", childrenList1);
        Map m11 = Maps.newLinkedHashMap();
        childrenList1.add(m11);
        m11.put("name", "查询用户");
        m11.put("path", "analysis");
        Map m12 = Maps.newLinkedHashMap();
        childrenList1.add(m12);
        m12.put("name", "删除用户");
        m12.put("path", "monitor");
        Map m13 = Maps.newLinkedHashMap();
        childrenList1.add(m13);
        m13.put("name", "新增用户");
        m13.put("path", "workplace");*/

        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        List<Map> menuList = sysPermissionService.selectPermissionByUserId(userInfo.getUid());

        return  AjaxResult.success(menuList, "查询菜单成功");

    }

}
