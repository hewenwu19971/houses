package com.hww.houserent.controller;

import com.hww.houserent.entity.UserEntity;
import com.hww.houserent.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value = "/tologin")
    public String tologin() {
        return "login.html";
    }

    @PostMapping(value = "/login")
    public String loging(UserEntity user, HttpSession session) {
        System.out.println("user对象：》》》》》》》》》》"+user);
        //获取当前主体
        Subject subject = SecurityUtils.getSubject();

        //创建令牌
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        System.out.println(">>>>>>>>>>>>>>>"+token);
        try {
            //登录
            subject.login(token);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误登录失败");
            return "redirect:/login.html";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误登录失败");
            return "redirect:/login.html";
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("不晓得什么异常");
            return "redirect:/login.html";
        }
        session.setAttribute("username",user.getUsername());
        return "redirect:/index.html";
    }

    /**
     * 登出操作，返回到登录页面
     * @param session
     * @return
     */
    @RequestMapping("loginout")
    public String loginOut(HttpSession session){
        session.removeAttribute("username");
        //获取当前主体
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.html";
    }

    @RequestMapping("/noAuthc")
    public String noAuthc(){
        return "redirect:/noAuthc.html";
    }

}
