package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.SysPermission;
import com.wx.springbootdemo.mapper.SysPermissionMapper;
import com.wx.springbootdemo.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> selectByRoleId(int roleId) {
        return sysPermissionMapper.selectByRoleId(roleId);
    }

}
