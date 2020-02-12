package com.xiaoman.mapper;

import com.xiaoman.dao.group;

public interface groupMapper {
    int deleteByPrimaryKey(Integer groupId);

    int insert(group record);

    int insertSelective(group record);

    group selectByPrimaryKey(Integer groupId);

    int updateByPrimaryKeySelective(group record);

    int updateByPrimaryKey(group record);
}