package com.xiaoman.service;

import com.xiaoman.dao.User;
import com.xiaoman.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User Login(String name, String password){
        
        return userMapper.Login(name,password);
    }

    public void regist(String name,String password){
        userMapper.regist(name,password);
    }
    public User selectByname(String name){
        return userMapper.selectByname(name);
    }
    public List<User> listAllMember(Integer leaderId){
        return userMapper.selectByGroup(leaderId);
    }

}
