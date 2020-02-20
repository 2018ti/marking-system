package com.xiaoman;

import com.xiaoman.dao.DoneWork;
import com.xiaoman.dao.marking;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.markingMapper;
import com.xiaoman.mapper.textMapper;
import com.xiaoman.service.UserService;
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
	markingMapper markingMapper;

	@Test
	void contextLoads() throws IOException {
		Date date = new Date();
		String timestamp = String.valueOf(date.getTime());
		System.out.println(timestamp);
		String fileurl = timestamp;
		Document dom = DocumentHelper.createDocument();//添加节点用addElement，添加节点属性用addAttribute,未节点赋值用setText
		Element Document = dom.addElement("Document"); //ServiceTab
		Document.addAttribute("ID",String.valueOf(1));
		Element event=Document.addElement("event");
		event.addAttribute("ID",String.valueOf(2));
		event.addAttribute("TYPE","会见会谈");
		Element event_trigger = event.addElement("event_Trigger");
		event_trigger.addAttribute("START",String.valueOf(26));
		event_trigger.addAttribute("END",String.valueOf(28));
		event_trigger.setText("会谈");
		OutputFormat format = OutputFormat.createPrettyPrint();//缩减型格式
		format.setEncoding("UTF-8");//设置编码
		format.setTrimText(false);
		Writer fileWriter = new FileWriter(fileurl+".xml");
		//dom4j提供了专门写入文件的对象XMLWriter
		XMLWriter xmlWriter = new XMLWriter(fileWriter,format);
		xmlWriter.write(dom);
		xmlWriter.flush();
		xmlWriter.close();
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

