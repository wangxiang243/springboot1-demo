package com.wx.springbootdemo.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysPermission;
import com.wx.springbootdemo.mapper.SysPermissionMapper;
import com.wx.springbootdemo.service.SysPermissionService;
import com.wx.springbootdemo.util.TreeUtils;
import org.apache.commons.lang3.StringUtils;
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
    public List<Map> selectPermissionByUserId(Map param) {

        List<SysPermission> sysPermissionList = sysPermissionMapper.selectPermissionByUserId(param);
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

    @Override
    public List<Map> getPermissionTree(Map param) {
        List<SysPermission> sysPermissionList = sysPermissionMapper.selectSysPermissionList(param);
        Map node;
        List<Map> nodeList = Lists.newArrayList();
        for(SysPermission sysPermission : sysPermissionList) {
            node = Maps.newLinkedHashMap();
            node.put("label", sysPermission.getName());
            node.put("value", sysPermission.getId().toString());
            node.put("key", sysPermission.getId().toString());
            node.put(TreeUtils.ID, sysPermission.getId());
            node.put(TreeUtils.PID, sysPermission.getParentId());
            node.put("name", sysPermission.getName());
            node.put("icon", sysPermission.getIcon());
            node.put("path", sysPermission.getUrl());
            node.put("state", sysPermission.getAvailable());
            node.put("permission", sysPermission.getPermission());
            node.put("resourceType", sysPermission.getResourceType());
            nodeList.add(node);
        }

        Map nodeTree = Maps.newLinkedHashMap();
        nodeTree.put("label", "顶级菜单");
        nodeTree.put("value", "0");
        nodeTree.put("key", "0");
        TreeUtils.getTreeNode(nodeList, TreeUtils.ROOT_ID, nodeTree, TreeUtils.CHILDREN);
        LOGGER.info("===nodeTree:[{}]===", nodeTree);
        List<Map> treeList = Lists.newArrayList();
        treeList.add(nodeTree);
        return treeList;
    }

    @Override
    public Map selectSysPermissionList(Map param) {
        return null;
    }

    @Override
    public int saveSyspermission(Map param) {

        SysPermission pSysPermission = sysPermissionMapper.selectByPrimaryKey(Integer.valueOf(param.get("pid").toString()));
        if(pSysPermission == null) {
            param.put("permission", "");
            param.put("parentIds", "0/");
            param.put("parentId", "0");
        } else {
            param.put("permission", pSysPermission.getUrl()+":"+param.get("url"));
            param.put("parentIds", pSysPermission.getParentIds()+"/"+pSysPermission.getId());
            param.put("parentId", pSysPermission.getId());
        }
        if(param.get("id") != null && StringUtils.isNotBlank(param.get("id").toString())) {
            SysPermission sysPermission = new SysPermission(Integer.valueOf(param.get("id").toString()), Integer.valueOf(param.get("available").toString()), param.get("name").toString(), Long.valueOf(param.get("parentId").toString()), param.get("parentIds").toString(), param.get("permission").toString(), param.get("resourceType").toString(), param.get("url").toString(), "");
            return sysPermissionMapper.updateByPrimaryKeySelective(sysPermission);
        }
        return sysPermissionMapper.saveSyspermission(param);
    }

    @Override
    public int deleteByIds(Integer id) {
        Map param = Maps.newLinkedHashMap();
        List<SysPermission> sysPermissionList = sysPermissionMapper.selectSysPermissionList(param);
        Map node;
        List<Map> nodeList = Lists.newArrayList();
        for(SysPermission sysPermission : sysPermissionList) {
            node = Maps.newLinkedHashMap();
            node.put(TreeUtils.ID, sysPermission.getId());
            node.put(TreeUtils.PID, sysPermission.getParentId());
            node.put("name", sysPermission.getName());
            nodeList.add(node);
        }

        List<Map> childrenList = Lists.newArrayList();
        TreeUtils.getChildrenNode(nodeList, id.toString(), childrenList);
        Map pNode = Maps.newLinkedHashMap();
        pNode.put("id", id);
        childrenList.add(pNode);
        return sysPermissionMapper.deleteByIds(childrenList);
    }

    @Override
    public SysPermission getSyspermissionById(Integer id) {
        return sysPermissionMapper.selectByPrimaryKey(id);
    }

}
