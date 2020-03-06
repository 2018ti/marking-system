package com.xiaoman.controller;

import com.xiaoman.dao.apply;
import com.xiaoman.dao.group;
import com.xiaoman.dao.text;
import com.xiaoman.dto.ResultText;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.service.UserService;
import com.xiaoman.service.groupService;
import com.xiaoman.service.textService;
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
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    textService textService;

    @Autowired
    groupService groupService;


    @Autowired
    UserMapper userMapper;

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
            }else {
                result.put("msg","系统管理员");
                return  result;
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

    @GetMapping("/listMember")
    public List<User> listMembers(HttpServletRequest request){
        User user =(User) request.getSession().getAttribute("user");
        return userService.listAllMember(Integer.valueOf(user.getGroupId()));
    }

    @GetMapping("/getLeader")
    public User getLeader(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        return user;
    }

    @GetMapping("/listText")
    public List<ResultText> getMyText(@RequestParam("leaderName")String leader){
        return textService.selectLeaderText(leader);
    }

    @GetMapping("/getsysmember")
    public List<User> listallUser(){
        return userService.listAllSysMember();
    }

    @PostMapping("/applyToLeader")
    public Map<String,String> applyToLeader(HttpServletRequest request){
       User user = (User)request.getSession().getAttribute("user");
       return userService.applytoleader(user.getName(),user.getRole());
    }

    @GetMapping("/getapplyList")
    public List<apply> applyList(){
        System.out.println("进入applylist");
        return userService.listallApply();
    }

    @PostMapping("/applyByadmin")
    public String applyit(@RequestParam("applyId")Integer applyId,@RequestParam("username")String username){
        userService.applyByadmin(applyId,username);
        userMapper.joingroup(username,null);
        return "批准成功";
    }

    @PostMapping("/joingroup")
    public String joingroup(@RequestParam("groupId") Integer groupId,HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        userService.joingroup(groupId,user.getName());
        User newUser = userMapper.selectById(user.getId());
        request.getSession().removeAttribute("user");
        request.getSession().setAttribute("user",newUser);
        return "加入成功";
    }

    @PostMapping("/createGroup")
    public String creategroup(@RequestParam("groupName")String groupName,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        System.out.println(groupName);
        int groupId = groupService.creatGroup(groupName, user.getName());
        userMapper.joingroup(user.getName(),groupId);
        User newUser = userMapper.selectById(user.getId());
        request.getSession().removeAttribute("user");
        request.getSession().setAttribute("user",newUser);
        return "创建成功";
    }
}
