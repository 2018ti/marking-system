package com.xiaoman.service;

import com.xiaoman.dao.text;
import com.xiaoman.dto.ToDoMarking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        for(text text:texts){
            String content=text.getContent();
            String leader=text.getLeader();
            int textId=text.getTextId();
            ToDoMarking toDoMarking = new ToDoMarking();
            toDoMarking.setContent(content);
            toDoMarking.setLeader(leader);
            toDoMarking.setTextId(textId);
            toDoMarkings.add(toDoMarking);
        }
        return toDoMarkings;
    }




}
