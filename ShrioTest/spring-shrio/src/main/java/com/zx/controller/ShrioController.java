/**
 * @Project Name:ShrioTest
 * @Package Name:com.zx.controller
 */
package com.zx.controller;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zx.shiro.realms.PasswordUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Author: ZC
 * @Email: chao_actor@163.com
 * @data: 2018/10/30 21:25
 */
@Controller
@RequestMapping("/shrioController")
public class ShrioController {

    private Logger log = LoggerFactory.getLogger(ShrioController.class);

    @RequestMapping("/index")
    public String index(){
        return "login";
    }

    /**
     *@Description: 验证信息
     *@Author: ZC
     *@Email: chao_actor@163.com
     *@TIME： 2018/10/30 22:34
     *@Params: [username, password]
     *@ReturnType: java.lang.String
     */
    @RequestMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("Password") String password
    ){
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return "login";
        }

//        打印密码对应的密文；
        System.out.println(PasswordUtil.enByMD5(username,password));;

        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                subject.login(token);
            }catch (Exception e){
                log.info("验证信息失败！");
                return "login";
            }
        }

        if (subject.isRemembered()){
            log.info("通过记住密码登录！");
        }else {
            log.info("不是通过记住密码登录！");
        }
        return "list";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }
}
