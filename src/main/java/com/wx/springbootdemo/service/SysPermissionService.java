package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {

    List<SysPermission> selectByRoleId(int roleId);

}
