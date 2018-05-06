package com.wx.springbootdemo.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysPermission;
import com.wx.springbootdemo.mapper.SysPermissionMapper;
import com.wx.springbootdemo.service.SysPermissionService;
import com.wx.springbootdemo.util.TreeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> selectByRoleId(int roleId) {
        return sysPermissionMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Map> selectPermissionByUserId(int uid) {

        List<SysPermission> sysPermissionList = sysPermissionMapper.selectPermissionByUserId(uid);
        Map node;
        List<Map> nodeList = Lists.newArrayList();
        for(SysPermission sysPermission : sysPermissionList) {
            node = Maps.newLinkedHashMap();
            node.put("name", sysPermission.getName());
            node.put("icon", sysPermission.getIcon());
            node.put("path", sysPermission.getUrl());
            node.put(TreeUtils.ID, sysPermission.getId());
            node.put(TreeUtils.PID, sysPermission.getParentId());
            nodeList.add(node);
        }

        Map nodeTree = Maps.newLinkedHashMap();
        TreeUtils.getTreeNode(nodeList, TreeUtils.ROOT_ID, nodeTree, TreeUtils.CHILDREN);
        LOGGER.info("===nodeTree:[{}]===", nodeTree);
        List<Map> treeList = (List<Map>) nodeTree.get(TreeUtils.CHILDREN);
        return treeList;
    }



}
