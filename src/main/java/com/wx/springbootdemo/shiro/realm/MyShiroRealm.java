package com.wx.springbootdemo.shiro.realm;

import com.wx.springbootdemo.entity.SysPermission;
import com.wx.springbootdemo.entity.SysRole;
import com.wx.springbootdemo.entity.UserInfo;
import com.wx.springbootdemo.service.SysPermissionService;
import com.wx.springbootdemo.service.SysRoleService;
import com.wx.springbootdemo.service.UserInfoService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class MyShiroRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<SysRole> sysRoleList = sysRoleService.selectByUid(userInfo.getUid());
        if(sysRoleList != null && sysRoleList.size() > 0) {
            List<SysPermission> sysPermissionList;
            for(SysRole sysRole : sysRoleList) {
                authorizationInfo.addRole(sysRole.getRole());
                sysPermissionList = sysPermissionService.selectByRoleId(sysRole.getId());
                if(sysPermissionList != null && sysPermissionList.size() > 0) {
                    for(SysPermission sysPermission : sysPermissionList) {
                        authorizationInfo.addStringPermission(sysPermission.getPermission());
                    }
                }
            }
        }

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo = userInfoService.selectByUsername(username);
        LOGGER.debug("===当前登录用户信息:[{}]===", userInfo);
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(
                userInfo, userInfo.getPassword(),
                ByteSource.Util.bytes(userInfo.getCredentialsSalt()), // salt=username+salt
                getName()
        );
        return simpleAuthenticationInfo;
    }

}
