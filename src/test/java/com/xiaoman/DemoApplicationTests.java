package com.xiaoman;

import com.xiaoman.dao.DoneWork;
import com.xiaoman.dao.marking;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.markingMapper;
import com.xiaoman.mapper.textMapper;
import com.xiaoman.service.UserService;
import com.xiaoman.service.textService;
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
	com.xiaoman.mapper.textMapper textMapper;



	@Autowired
	markingMapper markingMapper;
	@Autowired
	textService textService;
	@Test
	void contextLoads() {
		System.out.println(markingMapper.selectByUserIdAndText(21,1));

	}

	@Test
	void testUser(){
//		List<User> users = userMapper.ListAll();
//		for(User user : users){
//			System.out.println(user);
		List<DoneWorkResult> results = textService.ListDoneWork(21);
		for (DoneWorkResult doneWorkResult : results){
			System.out.println(doneWorkResult);
		}


	}
	}

