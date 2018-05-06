package com.wx.springbootdemo.util;

import org.apache.shiro.codec.Base64;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Base64Utils {

    /**
     * 获取文件的base64信息
     * @param filePath
     * @return
     */
    public static String getFileBase64(String filePath){

        try {
            String baseFile64 = Base64.encodeToString(Files.readAllBytes(Paths.get(filePath)));
            return baseFile64;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 根据base64生成文件
     * @param filePath
     * @param base64
     * @return
     */
    public static String generateFileByBase64(String filePath, String base64){

        try {
            Path path = Files.write(Paths.get(filePath), Base64.decode(base64));
            return path.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}
