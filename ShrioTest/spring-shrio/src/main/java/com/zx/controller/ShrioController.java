/**
 * @Project Name:ShrioTest
 * @Package Name:com.zx.controller
 */
package com.zx.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
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
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            token.setRememberMe(true);
            try {
                subject.login(token);
            }catch (Exception e){
                System.out.println("验证失败！");
                return "login";
            }
        }
        return "list";
    }
}
