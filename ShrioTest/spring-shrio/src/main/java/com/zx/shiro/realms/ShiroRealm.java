/**
 * @Project Name:ShrioTest
 * @Package Name:com.zx.shiro.realms
 */
package com.zx.shiro.realms;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/31 17:35
 */
public class ShiroRealm extends AuthorizingRealm {
    /**
     *@Description: 授权
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/10/31 17:36
     *@Params: [principals]
     *@ReturnType: org.apache.shiro.authz.AuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Object principal = principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        根据用户查询数据库中的用户的权限和角色
        System.out.println("查询：" + principal + "的角色和权限");
//        添加角色
        HashSet<String> roles = new HashSet<>();
        roles.add("user");
        authorizationInfo.addRoles(roles);
//        添加权限
        HashSet<String> permissions = new HashSet<>();
        authorizationInfo.addStringPermissions(permissions);

        return authorizationInfo;
    }

    /**
     *@Description: 认证
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/10/31 17:36
     *@Params: [token]
     *@ReturnType: org.apache.shiro.authc.AuthenticationInfo
     */
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
