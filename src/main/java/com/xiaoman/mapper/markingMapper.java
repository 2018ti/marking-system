package com.xiaoman.mapper;

import com.xiaoman.dao.marking;

public interface markingMapper {
    int deleteByPrimaryKey(Integer markingId);

    int insert(marking record);

    int insertSelective(marking record);

    marking selectByPrimaryKey(Integer markingId);

    int updateByPrimaryKeySelective(marking record);

    int updateByPrimaryKey(marking record);

    marking selectByUserId(Integer userId);
}