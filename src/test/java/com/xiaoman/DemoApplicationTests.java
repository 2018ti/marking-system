package com.xiaoman;

import com.xiaoman.dao.User;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.textMapper;
import com.xiaoman.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserService userService;
	@Autowired
	textMapper textMapper;
	@Test
	void contextLoads() {
		System.out.println(textMapper.selectToDoMarking(4));

	}

	@Test
	void testUser(){
//		List<User> users = userMapper.ListAll();
//		for(User user : users){
//			System.out.println(user);

		}
	}

