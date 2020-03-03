package com.xiaoman.service;

import com.xiaoman.dao.group;
import com.xiaoman.mapper.groupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class groupService {
    @Autowired
    groupMapper groupMapper;

    public group getGroupById(Integer groupId){
        return groupMapper.selectByPrimaryKey(groupId);
    }
    public List<group> listallGroup(){
        return groupMapper.listall();
    }

    public int creatGroup(String groupName,String leaderName){
        group group = new group();
        group.setLeader(leaderName);
        group.setGroupName(groupName);
        groupMapper.insertSelective(group);
        return group.getGroupId();
    }

}
