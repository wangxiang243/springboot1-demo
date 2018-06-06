package com.wx.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysRole;
import com.wx.springbootdemo.service.SysRoleService;
import com.wx.springbootdemo.util.AjaxResult;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/admin/sysrole/")
public class SysroleController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SysroleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("selectSysroleList")
    @RequiresPermissions(value = {"userinfo:auth", "role:auth"}, logical = Logical.OR)
    public AjaxResult selectSysroleList(@RequestParam Map param) {
        Map result = sysRoleService.selectRolePageList(param);
        return AjaxResult.success(result, "角色查询成功");
    }

    @RequestMapping("querySysuserroleByUID")
    @RequiresPermissions("userinfo:auth")
    public AjaxResult querySysuserroleByUID(@RequestParam Map param) {
        if(param == null || StringUtils.isBlank(param.get("uid").toString())) {
            LOGGER.error("===uid不能为空!===");
            return AjaxResult.fail("请先选择需要授权用户");
        }
        String uid = param.get("uid").toString();
        List<SysRole> sysRoleList = sysRoleService.selectByUid(Integer.valueOf(uid));
        List<Integer> idList = Lists.newArrayList();
        for(SysRole sysRole : sysRoleList) {
            idList.add(sysRole.getId());
        }
        return AjaxResult.success(idList, "查询授权角色成功");
    }

    @RequestMapping("saveRole")
    @RequiresPermissions(value = {"role:add", "role:edit"}, logical = Logical.OR)
    public AjaxResult saveRole(@RequestBody Map param) {
        if(param == null) {
            LOGGER.error("===角色信息不能为空===");
            return AjaxResult.fail("角色参数不能为空");
        }
        if(param.get("description") == null || StringUtils.isBlank(param.get("description").toString())) {
            return AjaxResult.fail("角色描述信息不能为空");
        }
        if(param.get("role") == null || StringUtils.isBlank(param.get("role").toString())) {
            return AjaxResult.fail("角色编码不能为空");
        }
        SysRole sysRole = new SysRole(param.get("description").toString(), param.get("role").toString());
        if(param.get("available") != null && StringUtils.isNotBlank(param.get("available").toString())) {
            sysRole.setAvailable(Integer.valueOf(param.get("available").toString()));
        }
        if(param.get("id") != null && StringUtils.isNotBlank(param.get("id").toString())) {
            sysRole.setId(Integer.valueOf(param.get("id").toString()));
            sysRoleService.updateRole(sysRole);
        } else {
            sysRoleService.saveRole(sysRole);
        }
        return AjaxResult.success("角色保存成功");
    }

    @RequestMapping("selectById")
    @RequiresPermissions("role:list")
    public AjaxResult selectById(Integer id) {
        if(id == null) {
            LOGGER.error("===id不能为空===");
            return AjaxResult.fail("id不能为空");
        }
        SysRole sysRole = sysRoleService.selectById(id);
        return AjaxResult.success(sysRole, "查询角色成功");
    }

    @RequestMapping("updateRole")
    @RequiresPermissions("role:edit")
    public AjaxResult updateRole(SysRole sysRole) {
        if(sysRole == null) {
            LOGGER.error("===修改的角色不能为空");
            return AjaxResult.fail("修改的角色不能为空");
        }
        if(sysRole.getId() == null) {
            return AjaxResult.fail("角色ID不能为空");
        }
        if(StringUtils.isBlank(sysRole.getRole())) {
            return AjaxResult.fail("角色编码不能为空");
        }
        if(StringUtils.isBlank(sysRole.getDescription())) {
            return AjaxResult.fail("角色描述不能为空");
        }
        sysRoleService.updateRole(sysRole);
        return AjaxResult.success("角色修改成功");
    }

    @RequestMapping("deleteRoleById")
    @RequiresPermissions("role:del")
    public AjaxResult  deleteRoleById(Integer id) {
        if(id == null) {
            return AjaxResult.fail("角色ID不能为空");
        }
        sysRoleService.deleteRoleById(id);
        return AjaxResult.success("角色删除成功");
    }

    @RequestMapping("selectSyspermissionByRoleId")
    @RequiresPermissions("role:auth")
    public AjaxResult selectSyspermissionByRoleId(Integer roleId) {
        if(roleId == null) {
            return AjaxResult.fail("角色ID不能为空");
        }
        List<Integer> permissionIds = sysRoleService.selectSyspermissionByRoleId(roleId);
        return AjaxResult.success(permissionIds, "根据角色获取权限成功");
    }

    @RequestMapping("saveSysRolePermission")
    @RequiresPermissions("role:auth")
    public AjaxResult saveSysRolePermission(@RequestBody Map param) {
        if(param == null) {
            return AjaxResult.fail("角色授权不能为空");
        }
        if(param.get("selectedRows") == null || StringUtils.isBlank(param.get("selectedRows").toString())) {
            return AjaxResult.fail("请选择需要授权的角色");
        }
        if(param.get("checkedKeys") == null || StringUtils.isBlank(param.get("checkedKeys").toString())) {
            return AjaxResult.fail("请选择权限信息");
        }
        List<Map> roleList = JSON.parseArray(param.get("selectedRows").toString(), Map.class);
        Integer roleId = Integer.valueOf(roleList.get(0).get("id").toString());
        List<Integer> permissionIds = JSON.parseArray(param.get("checkedKeys").toString(), Integer.class);
        List<Map> sysRolePermissionList = Lists.newArrayList();
        Map sysRolePermissionMap;
        for(Integer permissionId : permissionIds) {
            sysRolePermissionMap = Maps.newLinkedHashMap();
            sysRolePermissionMap.put("permissionId", permissionId);
            sysRolePermissionMap.put("roleId", roleId);
            sysRolePermissionList.add(sysRolePermissionMap);
        }
        sysRoleService.saveSysRolePermission(sysRolePermissionList);
        return AjaxResult.success("角色授权成功");
    }

}
