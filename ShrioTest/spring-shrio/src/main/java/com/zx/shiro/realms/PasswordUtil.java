/**
 * @Project Name:ShrioTest
 * @Package Name:com.zx.shiro.realms
 */
package com.zx.shiro.realms;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/31 12:38
 */
public class PasswordUtil {
    public static String enByMD5(String username,String password){
//      加密算法；
        String algorithmName = "MD5";
//      加密次数；
        int hashIterations = 1024;
//      加密后的结果
        Object result = new SimpleHash( algorithmName,  password,  username,  hashIterations);

        return result.toString();
    }

    public static void main(String[] args) {
        String admin = enByMD5("admin", "123456");
        System.out.println(admin);
    }
}
