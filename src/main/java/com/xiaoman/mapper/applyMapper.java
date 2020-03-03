package com.xiaoman.mapper;

import com.xiaoman.dao.apply;

import java.util.List;

public interface applyMapper {
    int deleteByPrimaryKey(Integer applyId);

    int insert(apply record);

    int insertSelective(apply record);

    apply selectByPrimaryKey(Integer applyId);

    int updateByPrimaryKeySelective(apply record);

    int updateByPrimaryKey(apply record);

    List<apply> listall();

    apply selectByUsername(String name);
}