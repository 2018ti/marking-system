package com.xiaoman.mapper;

import com.xiaoman.dao.type;

public interface typeMapper {
    int deleteByPrimaryKey(Integer typeId);

    int insert(type record);

    int insertSelective(type record);

    type selectByPrimaryKey(Integer typeId);

    int updateByPrimaryKeySelective(type record);

    int updateByPrimaryKey(type record);
}