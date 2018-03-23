package com.wx.springbootdemo;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

public class CommonTest {

    @Test
    public void test1() {

        System.out.println("XMLHttpRequest".equalsIgnoreCase("xmLhttprequest"));

    }

    @Test
    public void testEncrypt() {

        String salt = "8d78869f470951332959580424d4bf4f";
        HashRequest hashRequest = new HashRequest.Builder()
                .setAlgorithmName("md5").setSource(ByteSource.Util.bytes("123456"))
                .setSalt(ByteSource.Util.bytes("admin"+salt)).setIterations(2).build();
//        hashRequest.hashCode()
        DefaultHashService defaultHashService = new DefaultHashService();
        String pwd = defaultHashService.computeHash(hashRequest).toHex();
        System.out.println(pwd);


        SecureRandomNumberGenerator randomNumberGenerator =
                new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        String hex = randomNumberGenerator.nextBytes().toHex();
        System.out.println(hex);
    }

}
