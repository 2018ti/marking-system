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
}
