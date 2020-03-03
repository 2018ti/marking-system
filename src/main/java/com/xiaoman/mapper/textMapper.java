package com.xiaoman.mapper;

import com.xiaoman.dao.text;
import com.xiaoman.dao.DoneWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface textMapper {
    int deleteByPrimaryKey(Integer textId);

    int insert(text record);

    int insertSelective(text record);

    text selectByPrimaryKey(Integer textId);

    int updateByPrimaryKeySelective(text record);

    int updateByPrimaryKey(text record);

    List<text> selectToDoMarking(@Param("userId")Integer userId,@Param("leader")String leader);

    List<DoneWork> selectDoneWorkTextTable(@Param("userId")Integer userId);

    //查询该用户组长下一致性为100%的标记
    List<DoneWork> selectMarkedText(String leaderName);

    List<text> selectLeaderText(String leader);

    List<text> selectByK(Double K);

    DoneWork selectByTextAndUser(@Param("userId") Integer userId,@Param("textId")Integer textId);
}