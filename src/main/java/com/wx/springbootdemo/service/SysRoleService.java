package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.SysRole;

import java.util.List;

public interface SysRoleService {

    List<SysRole> selectByUid(int uid);

}
