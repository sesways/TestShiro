/**
 * @Project Name:ShrioTest
 * @Package Name:com.zx.shiro.realms
 */
package com.zx.shiro.realms;

import jdk.nashorn.internal.parser.Token;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

import java.util.Arrays;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/30 21:54
 */
public class ShrioReaml extends AuthenticatingRealm {
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SimpleAuthenticationInfo info = null;
//        获取前台输入的账号；
        String userName = token.getUsername();
//        获取前台输入的密码，此时的密码已经净多MD5加密，与配置文件配置的一致；
//        在生产环境中该密码就是数据库中保存的密码，PasswordUtil中已经有相应的加密方法；
        String passWord = null;
//        RealmName
        String realmName = getName();
//        计算盐值；
        ByteSource credentialsSalt = ByteSource.Util.bytes(userName);
        if ("admin".equals("admin")){
//          当加密算法为MD5，加密次数为1024，盐值为：admin时的加密值；
            passWord = "038bdaf98f2037b31f1e75b5b4c9b26e";
        }
//        构造认证器，认证器会根据参数自动完成匹配；
        info = new SimpleAuthenticationInfo(userName,passWord,credentialsSalt,realmName);
        return info;
    }
}
