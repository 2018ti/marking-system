package com.xiaoman.controller;

import com.xiaoman.mapper.UserMapper;
import com.xiaoman.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.geom.FlatteningPathIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xiaoman.dao.User;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public Map<String,String> Login(@RequestParam("name")String name, @RequestParam("password")String password, HttpServletRequest request){
        HashMap<String, String> result = new HashMap<>();
        User user = userService.Login(name, password);
        request.getSession().setAttribute("user",user);
        if(user!=null){
            if(user.getRole().equals("普通用户")){
                System.out.println(user);
                result.put("msg","普通用户");
                return result;
            }else if(user.getRole().equals("用户组长")){
                result.put("msg","用户组长");
                return result;
            }
        }else
            result.put("msg","登录失败");
        return result;
    }

    @PostMapping("/regist")
    public Map<String,String> regist(@RequestParam("name")String name, @RequestParam("password")String password){
        HashMap<String, String> result= new HashMap<>();
        if(userService.selectByname(name)!=null){
            result.put("msg","0");
            return result;
        }else {
            userService.regist(name, password);
            result.put("msg","1");
            return result;
        }
    }



}
