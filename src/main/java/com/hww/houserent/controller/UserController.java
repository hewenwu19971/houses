package com.hww.houserent.controller;

import com.hww.houserent.entity.UserEntity;
import com.hww.houserent.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 登录操作
     * @param username
     * @param password
     * @param session
     * @param modelMap
     * @return
     */
    @RequestMapping("/signin")
    public String login(@RequestParam(name = "username")String username,
                        @RequestParam(name = "password")String password,
                        HttpSession session, ModelMap modelMap){
        UserEntity userEntity = userService.login(username);
        String oldpassword = userEntity.getPassword();
        if (oldpassword != null){
            if (password.equals(oldpassword)){
                session.setAttribute("username",username);
                return "redirect:/index.html";
            }
        }
        return "redirect:/login.html";
    }

    /**
     * 登出操作，返回到登录页面
     * @param session
     * @return
     */
    @RequestMapping("loginout")
    public String loginOut(HttpSession session){
        session.removeAttribute("username");
        return "redirect:/login.html";
    }

}
