package com.wx.springbootdemo.service.impl;

import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysRole;
import com.wx.springbootdemo.mapper.SysRoleMapper;
import com.wx.springbootdemo.service.SysRoleService;
import com.wx.springbootdemo.util.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public List<SysRole> selectByUid(int uid) {
        return sysRoleMapper.selectByUid(uid);
    }

    @Override
    public Map selectRolePageList(Map param) {

//        int start = PageView.getStart(param);
//        int pageSize = PageView.getPageSize(param);
//        param.put("start", start);
//        param.put("pageSize", pageSize);
//        int count = sysRoleMapper.selectRoleCount(param);
        List<Map> sysRoleList = sysRoleMapper.selectRoleList(param);
//        Map r = PageView.getPageView(param, count, sysRoleList);
        Map result = PageView.getNoPageView(sysRoleList);
        return result;
    }


}
