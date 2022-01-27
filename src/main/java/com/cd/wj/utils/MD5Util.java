package com.cd.wj.utils;

import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.io.InputStream;

public class MD5Util {

    /**
     * 使用spring自带的DigestUtils实现md5加密
     * @param bytes 字节
     * @return string
     */
    public static String getMD5ByBytes(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    /**
     * 使用spring自带的DigestUtils实现md5加密
     * @param in 输入流
     * @return string
     */
    public static String getMD5ByInputStream(InputStream in) {
        try {
            return DigestUtils.md5DigestAsHex(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
