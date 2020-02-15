package com.xiaoman.service;

import com.xiaoman.dao.text;
import com.xiaoman.dto.ToDoMarking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class textService {

    @Autowired
    com.xiaoman.mapper.textMapper textMapper;

    public void insertText(String content,String leader){
        text text = new text();
        text.setContent(content);
        text.setLeader(leader);
        textMapper.insertSelective(text);
    }

    public List<ToDoMarking> searchToDoMarking(Integer userId){
        List<text> texts = textMapper.selectToDoMarking(userId);
        List<ToDoMarking> toDoMarkings = new ArrayList<ToDoMarking>();
        long time1 = 1527767665;
        for(text text:texts){
            String content=text.getContent();
            String leader=text.getLeader();
            Date loadTime=text.getLoadTime();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattime = format.format(loadTime);
            int textId=text.getTextId();
            ToDoMarking toDoMarking = new ToDoMarking();
            toDoMarking.setContent(content);
            toDoMarking.setLeader(leader);
            toDoMarking.setTextId(textId);
            toDoMarking.setLoadTime(formattime);
            toDoMarkings.add(toDoMarking);
        }
        return toDoMarkings;
    }

    public text getTxtById(int textId){
        return textMapper.selectByPrimaryKey(textId);
    }




}
