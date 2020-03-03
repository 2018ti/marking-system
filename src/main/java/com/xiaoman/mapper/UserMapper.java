package com.xiaoman.mapper;

import com.xiaoman.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public List<User> ListAll();


    public User Login(@Param("name") String name, @Param("password") String password);

    public void regist(@Param("name")String name,@Param("password")String password);

    public User selectByname(@Param("name")String name);

    public Integer countGroupMember(@Param("userId")Integer userId);

    public List<User> selectByGroup(@Param("groupId")Integer groupId);

    public Integer selectUserId(@Param("name")String name);

    public void updateToLeaderByname(@Param("name")String name);

    public void joingroup(@Param("name")String name,@Param("groupId")Integer groupId);

    public User selectById(Integer userId);
}
