package com.xiaoman.service;

import com.xiaoman.dao.User;
import com.xiaoman.dao.apply;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.applyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    applyMapper applyMapper;

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

    public List<User> listAllSysMember(){
        return userMapper.ListAll();
    }

    public Map<String,String> applytoleader(String name, String role){
        Map result= new HashMap<String,String>();
        if(applyMapper.selectByUsername(name)!=null){
            result.put("msg","您已经申请过了，请等待用户组长同意");
            return result;
        }
        apply apply= new apply();
        apply.setUserName(name);
        apply.setApplyRole(role);
        apply.setStatus("待批准");
        applyMapper.insertSelective(apply);
        result.put("msg","成功提交申请");
        return result;
    }

    public List<apply> listallApply(){
        return applyMapper.listall();
    }

    public void applyByadmin(Integer applyId,String username){
        apply apply = new apply();
        apply.setApplyId(applyId);
        apply.setStatus("通过");
        applyMapper.updateByPrimaryKeySelective(apply);
        userMapper.updateToLeaderByname(username);
    }
    public void joingroup(Integer groupId,String username){
        userMapper.joingroup(username,groupId);
    }
}
