package com.wx.springbootdemo.controller;

import com.google.common.collect.Lists;
import com.wx.springbootdemo.entity.SysRole;
import com.wx.springbootdemo.service.SysRoleService;
import com.wx.springbootdemo.util.AjaxResult;
import com.wx.springbootdemo.util.UserUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/sysrole/")
public class SysroleController {

    public static final Logger LOGGER = LoggerFactory.getLogger(SysroleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping("selectSysroleList")
    @RequiresPermissions("userinfo:auth")
    public AjaxResult selectSysroleList(@RequestParam Map param) {
        Map result = sysRoleService.selectRolePageList(param);
        return AjaxResult.success(result, "角色查询成功");
    }

    @RequestMapping("querySysuserroleByUID")
    @RequiresPermissions("userinfo:auth")
    public AjaxResult querySysuserroleByUID(@RequestParam Map param) {
        if(param == null || StringUtils.isBlank(param.get("uid").toString())) {
            LOGGER.error("===uid不能为空!===");
            return AjaxResult.fail("请先选择需要授权用户");
        }
        String uid = param.get("uid").toString();
        List<SysRole> sysRoleList = sysRoleService.selectByUid(Integer.valueOf(uid));
        List<Integer> idList = Lists.newArrayList();
        for(SysRole sysRole : sysRoleList) {
            idList.add(sysRole.getId());
        }
        return AjaxResult.success(idList, "查询授权角色成功");
    }

}
