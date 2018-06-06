package com.wx.springbootdemo.service.impl;

import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysRole;
import com.wx.springbootdemo.mapper.SysRoleMapper;
import com.wx.springbootdemo.service.SysRoleService;
import com.wx.springbootdemo.util.PageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public int saveRole(SysRole sysRole) {
        return sysRoleMapper.insertSelective(sysRole);
    }

    @Override
    public int updateRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKeySelective(sysRole);
    }

    @Override
    @Transactional
    public int deleteRoleById(Integer id) {
        Map param = Maps.newLinkedHashMap();
        param.put("roleId", id);
        int r1 = sysRoleMapper.deleteSysUserRole(param);
        int r = sysRoleMapper.deleteByPrimaryKey(id);
        return r1+r;
    }

    @Override
    public SysRole selectById(Integer id) {
        return sysRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Integer> selectSyspermissionByRoleId(Integer id) {
        return sysRoleMapper.selectSyspermissionByRoleId(id);
    }

    @Override
    @Transactional
    public int saveSysRolePermission(List<Map> sysRolePermissionList) {
        int r1 = sysRoleMapper.deleteSysRolePermission(Integer.valueOf(sysRolePermissionList.get(0).get("roleId").toString()));
        int r2 = sysRoleMapper.saveSysRolePermission(sysRolePermissionList);
        return r1+r2;
    }
}
