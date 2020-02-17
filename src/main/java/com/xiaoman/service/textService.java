package com.xiaoman.service;

import com.xiaoman.dao.DoneWork;
import com.xiaoman.dao.text;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.dto.ToDoMarking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<DoneWorkResult> ListDoneWork(Integer userId){
        List<DoneWork> doneWorks=textMapper.selectDoneWorkTextTable(userId);
        List<DoneWorkResult> results = new ArrayList<>();
        for(DoneWork doneWork : doneWorks){
            DoneWorkResult doneWorkResult = new DoneWorkResult();
            doneWorkResult.setContent(doneWork.getContent());
            doneWorkResult.setLeader(doneWork.getLeader());
            doneWorkResult.setTextId(doneWork.getTextId());
            if(doneWork.getMarking().getEventType().equals("会见会谈")){
                doneWorkResult.setMarking1(doneWork.getMarking().getParticipant1());
                doneWorkResult.setMarking2(doneWork.getMarking().getParticipant2());
                doneWorkResult.setMarking3(doneWork.getMarking().getMeetingTime());
                doneWorkResult.setMarking4(doneWork.getMarking().getMeetingPlace());
                doneWorkResult.setTrigger(doneWork.getMarking().getTrigger());
                doneWorkResult.setEventType("会见会谈");
            }else if (doneWork.getMarking().getEventType().equals("签署文件")){
                doneWorkResult.setMarking1(doneWork.getMarking().getSignatory());
                doneWorkResult.setMarking2(doneWork.getMarking().getFile());
                doneWorkResult.setMarking3(doneWork.getMarking().getFileTime());
                doneWorkResult.setMarking4(doneWork.getMarking().getFilePlace());
                doneWorkResult.setTrigger(doneWork.getMarking().getTrigger());
                doneWorkResult.setEventType("签署文件");
            }else if(doneWork.getMarking().getEventType().equals("设施启用")){
                doneWorkResult.setMarking1(doneWork.getMarking().getConstructor());
                doneWorkResult.setMarking2(doneWork.getMarking().getBuildingName());
                doneWorkResult.setMarking3(doneWork.getMarking().getStartingTime());
                doneWorkResult.setMarking4(doneWork.getMarking().getBuildingPlace());
                doneWorkResult.setTrigger(doneWork.getMarking().getTrigger());
                doneWorkResult.setEventType("设施启用");
            }else {
                doneWorkResult.setMarking1(doneWork.getMarking().getHolder());
                doneWorkResult.setMarking2(doneWork.getMarking().getActivityName());
                doneWorkResult.setMarking3(doneWork.getMarking().getActivityPlace());
                doneWorkResult.setMarking4(doneWork.getMarking().getAvtivityTime());
                doneWorkResult.setTrigger(doneWork.getMarking().getTrigger());
                doneWorkResult.setEventType("举行活动");
            }
            results.add(doneWorkResult);
        }
        return results;
    }

    public text getTxtById(int textId){
        return textMapper.selectByPrimaryKey(textId);
    }




}
