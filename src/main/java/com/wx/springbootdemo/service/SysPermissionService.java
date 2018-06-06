package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.SysPermission;

import java.util.List;
import java.util.Map;

public interface SysPermissionService {

    List<SysPermission> selectByRoleId(int roleId);
    List<Map> selectPermissionByUserId(Map param);
    List<Map> getPermissionTree(Map param);

    Map selectSysPermissionList(Map param);

    int saveSyspermission(Map param);
    int deleteByIds(Integer ids);

    SysPermission getSyspermissionById(Integer id);

}
