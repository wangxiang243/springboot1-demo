package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

    List<SysRole> selectByUid(int uid);

    Map selectRolePageList(Map param);

    int saveRole(SysRole sysRole);

    SysRole selectById(Integer id);

    int updateRole(SysRole sysRole);

    int deleteRoleById(Integer id);

    List<Integer> selectSyspermissionByRoleId(Integer roleId);

    int saveSysRolePermission(List<Map> sysRolePermissionList);

}
