package com.xiaoman;

import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.textMapper;
import com.xiaoman.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserService userService;
	@Autowired
	com.xiaoman.mapper.textMapper textMapper;
	@Test
	void contextLoads() {
		System.out.println(userMapper.Login("非凡","123"));
	}

	@Test
	void testUser(){
//		List<User> users = userMapper.ListAll();
//		for(User user : users){
//			System.out.println(user);

		}
	}

