package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.SysRole;

import java.util.List;
import java.util.Map;

public interface SysRoleService {

    List<SysRole> selectByUid(int uid);

    Map selectRolePageList(Map param);

}
