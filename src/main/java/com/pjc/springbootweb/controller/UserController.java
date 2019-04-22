package com.pjc.springbootweb.controller;/*
 *@author: PJC
 *@time: 2019/4/9
 *@description: null
 */

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class UserController {

    @PostMapping(value = "/user/login")
    public String login(@RequestParam String username, @RequestParam String password, Map<String, Object> maps, HttpSession session){
        if(!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else{
            maps.put("message","登录名密码错误！");
            return "login";
        }
    }

    @GetMapping(value = "/user/logout")
    public String logout(HttpServletRequest request){
        //获取当前session
        HttpSession session = request.getSession();
        //让当前session实效
        session.invalidate();
        //返回登录页面
        return "login";
    }
}
