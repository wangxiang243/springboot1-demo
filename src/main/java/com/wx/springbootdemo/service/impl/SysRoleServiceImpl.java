package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.SysRole;
import com.wx.springbootdemo.mapper.SysRoleMapper;
import com.wx.springbootdemo.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectByUid(int uid) {
        return sysRoleMapper.selectByUid(uid);
    }

}
