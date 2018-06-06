package com.wx.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysPermission;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.service.SysPermissionService;
import com.wx.springbootdemo.util.AjaxResult;
import com.wx.springbootdemo.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public AjaxResult getMenuData(String resourceType) {

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
        Map param = Maps.newLinkedHashMap();
        param.put("uid", userInfo.getUid());
        if(StringUtils.isNotBlank(resourceType)) {
            param.put("resourceType", resourceType);
        }
        List<Map> menuList = sysPermissionService.selectPermissionByUserId(param);

        return  AjaxResult.success(menuList, "查询菜单成功");

    }

    @RequestMapping("getPermissionList")
    @RequiresPermissions("permission:list")
    public AjaxResult getPermissionList() {
        Map param = Maps.newLinkedHashMap();
        List<Map> permissionList = sysPermissionService.getPermissionTree(param);
        List<Map> treeList = (List<Map>)permissionList.get(0).get(TreeUtils.CHILDREN);
        return  AjaxResult.success(treeList, "查询权限列表成功");
    }

    @RequestMapping("getPermissionTree")
    @RequiresPermissions("permission:add")
    public AjaxResult getPermissionTree(String resourceType) {

        UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        Map param = Maps.newLinkedHashMap();
//        param.put("uid", userInfo.getUid());
        if(StringUtils.isNotBlank(resourceType)) {
            param.put("resourceType", resourceType);
        }
        List<Map> menuList = sysPermissionService.getPermissionTree(param);

        return  AjaxResult.success(menuList, "查询权限树成功");

    }

    @RequestMapping("saveSyspermission")
    @RequiresPermissions("permission:add")
    public AjaxResult saveSyspermission(@RequestBody Map param) {
        LOGGER.info("===param:[{}]===", param);
//        Map paramMap = JSON.parseObject(param, Map.class);
        if(param == null || param.get("pid") == null || StringUtils.isBlank(param.get("pid").toString()) ) {
            LOGGER.error("请选择上级权限!");
            return AjaxResult.fail("请选择上级权限");
        }
        int r = sysPermissionService.saveSyspermission(param);
        return AjaxResult.success("权限保存成功!");
    }

    @RequestMapping("deleteSyspermissionById")
    @RequiresPermissions("permission:del")
    public AjaxResult deleteSyspermissionById(Integer id) {
        if(id == null) {
            return AjaxResult.fail("请指定要删除的权限id!");
        }
        sysPermissionService.deleteByIds(id);
        return AjaxResult.success("删除权限成功");
    }

    @RequestMapping("getSyspermissionById")
    @RequiresPermissions("permission:list")
    public AjaxResult getSyspermissionById(Integer id) {
        if(id == null) {
            return AjaxResult.fail("请指定权限id");
        }
        SysPermission sysPermission = sysPermissionService.getSyspermissionById(id);
        return AjaxResult.success(sysPermission, "获取权限成功");
    }

}
