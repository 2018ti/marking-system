package com.xiaoman;

import com.xiaoman.dao.DoneWork;
import com.xiaoman.dao.marking;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.markingMapper;
import com.xiaoman.mapper.textMapper;
import com.xiaoman.service.UserService;
import com.xiaoman.service.agreementService;
import com.xiaoman.service.groupService;
import com.xiaoman.service.textService;
import org.apache.ibatis.transaction.Transaction;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.rowset.spi.XmlWriter;
import javax.xml.crypto.Data;
import java.awt.geom.FlatteningPathIterator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.Iterator;
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
	textService textService;

	@Autowired
	agreementService  agreementService;

	@Autowired
	groupService groupService;

	@Autowired
	markingMapper markingMapper;

	@Test
	void contextLoads() throws IOException {
		System.out.println(textMapper.selectDoneWorkTextTable(21));
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

