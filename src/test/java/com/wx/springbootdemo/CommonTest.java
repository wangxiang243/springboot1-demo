package com.wx.springbootdemo;

import com.wx.springbootdemo.util.Base64Utils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;

import java.util.Base64;

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

        DefaultPasswordService defaultPasswordService = new DefaultPasswordService();
//        defaultPasswordService.creat
//        defaultPasswordService.

        String salt1 = new Md5Hash("wx"+"admin").toString();
        System.out.println(salt1);
        HashRequest hashRequest1 = new HashRequest.Builder().setAlgorithmName("md5").setSource(ByteSource.Util.bytes("123456")).setSalt("admin"+salt1).setIterations(2).build();
        String pwd1 = new DefaultHashService().computeHash(hashRequest1).toHex();
        System.out.println(pwd1);


    }

    public void a(){}
    protected void b(){}
    void c(){}
    private void d(){}


    @Test
    public void testbase64() {

        String sourcefilepath = "/Users/wangxiang/Desktop/sc.jpeg";
        String base64 = Base64Utils.getFileBase64(sourcefilepath);
        System.out.println(base64);

        String filepath = "/Users/wangxiang/Desktop/a.jpg";
        System.out.println(Base64Utils.generateFileByBase64(filepath, base64));


    }

    @Test
    public void testbyte() {
        byte b = Byte.parseByte("-128");
        System.out.println(b);
    }

}
