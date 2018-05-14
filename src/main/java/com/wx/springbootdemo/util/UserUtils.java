package com.wx.springbootdemo.util;

import com.wx.springbootdemo.entity.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;

public class UserUtils {

    public static UserInfo getCurrentUser() {
        return (UserInfo) SecurityUtils.getSubject().getPrincipal();
    }

    public static String getPassword(String sourcePassword, String salt) {
        HashRequest hashRequest = new HashRequest.Builder().setAlgorithmName("md5")
                .setSource(sourcePassword).setSalt(salt).setIterations(2).build();
        DefaultHashService defaultHashService = new DefaultHashService();
        return defaultHashService.computeHash(hashRequest).toHex();
    }

}
