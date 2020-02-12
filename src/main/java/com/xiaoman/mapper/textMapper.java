package com.xiaoman.mapper;

import com.xiaoman.dao.text;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface textMapper {
    int deleteByPrimaryKey(Integer textId);

    int insert(text record);

    int insertSelective(text record);

    text selectByPrimaryKey(Integer textId);

    int updateByPrimaryKeySelective(text record);

    int updateByPrimaryKey(text record);

    List<text> selectToDoMarking(@Param("userId") Integer userId);

}