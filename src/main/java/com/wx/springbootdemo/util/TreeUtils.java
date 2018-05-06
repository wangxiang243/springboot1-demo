package com.wx.springbootdemo.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.entity.SysPermission;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

public class TreeUtils {

    public static final String ROOT_ID = "0";
    public static final String ID = "id";
    public static final String PID = "pid";
    public static final String CHILDREN = "children";

    public static void getChildrenNode(List<Map> allNodeList, String pid, List<Map> childrenList) {
        for (Map map : allNodeList) {
            if (map.get(PID).toString().equals(pid)) {
                childrenList.add(map);
                getChildrenNode(allNodeList, map.get(ID).toString(), childrenList);
            }
        }
    }

    public static void getTreeNode(List<Map> allNodeList, String pid, Map treeNode, String childrenKey) {

        if(StringUtils.isBlank(childrenKey)) {
            childrenKey = CHILDREN;
        }

        List<Map> childrenList = Lists.newArrayList();
        for (Map node : allNodeList) {
            if (node.get(PID).toString().equals(pid)) {
                childrenList.add(node);
            }
        }
        if (childrenList == null || childrenList.size() == 0) {
            return;
        }
        treeNode.put(ID, pid);
        treeNode.put(childrenKey, childrenList);
        for (Map childrenNode : childrenList) {
            getTreeNode(allNodeList, childrenNode.get(ID).toString(), childrenNode, childrenKey);
        }

    }

    public static void main(String[] args) {

        List<Map> mapList = Lists.newArrayList();

        Map map1 = Maps.newLinkedHashMap();
        map1.put("id", 1);
        map1.put("pid", 0);
        mapList.add(map1);
        Map map11 = Maps.newLinkedHashMap();
        map11.put("id", 11);
        map11.put("pid", 1);
        mapList.add(map11);
        Map map12 = Maps.newLinkedHashMap();
        map12.put("id", 12);
        map12.put("pid", 1);
        mapList.add(map12);

        Map map2 = Maps.newLinkedHashMap();
        map2.put("id", 2);
        map2.put("pid", 0);
        mapList.add(map2);
        Map map21 = Maps.newLinkedHashMap();
        map21.put("id", 21);
        map21.put("pid", 2);
        mapList.add(map21);
        Map map22 = Maps.newLinkedHashMap();
        map22.put("id", 22);
        map22.put("pid", 2);
        mapList.add(map22);

        Map map3 = Maps.newLinkedHashMap();
        map3.put("id", 3);
        map3.put("pid", 0);
        mapList.add(map3);
        Map map31 = Maps.newLinkedHashMap();
        map31.put("id", 31);
        map31.put("pid", 3);
        mapList.add(map31);

        /*List<Map> childrenList = Lists.newArrayList();
        getChildrenNode(mapList, "0", childrenList);
        System.out.println(childrenList);*/

        Map treeNode = Maps.newLinkedHashMap();
        getTreeNode(mapList, "0", treeNode, "");
        System.out.println(JSON.toJSONString(treeNode));

    }

}
