package com.xiaoman.service;

import com.xiaoman.dao.DoneWork;
import com.xiaoman.dao.marking;
import com.xiaoman.dao.text;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.dto.ToDoMarking;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.markingMapper;
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
    
    @Autowired
    markingMapper markingMapper;

    @Autowired
    UserMapper userMapper;

    public void insertText(String content,String leader,String title){
        text text = new text();
        Date date = new Date();
        date.getTime();
        text.setContent(content);
        text.setLeader(leader);
        text.setTitle(title);
        text.setLoadTime(date);
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
            String title=text.getTitle();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattime = format.format(loadTime);
            int textId=text.getTextId();
            ToDoMarking toDoMarking = new ToDoMarking();
            toDoMarking.setContent(content);
            toDoMarking.setLeader(leader);
            toDoMarking.setTextId(textId);
            toDoMarking.setLoadTime(formattime);
            toDoMarking.setTitle(title);
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
            doneWorkResult.setMarkingId(doneWork.getMarking().getMarkingId());
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

    public DoneWorkResult getTextByMarkingId(Integer markingId){
        DoneWorkResult result = new DoneWorkResult();
        marking marking = markingMapper.selectByPrimaryKey(markingId);
        text text = textMapper.selectByPrimaryKey(marking.getTextId());
        result.setContent(text.getContent());
        result.setTrigger(marking.getTrigger());
        result.setEventType(marking.getEventType());
        if(marking.getEventType().equals("会见会谈")){
            result.setMarking1(marking.getParticipant1());
            result.setMarking2(marking.getParticipant2());
            result.setMarking3(marking.getMeetingTime());
            result.setMarking4(marking.getMeetingPlace());
        }else if(marking.getEventType().equals("签署文件")){
            result.setMarking1(marking.getSignatory());
            result.setMarking2(marking.getFile());
            result.setMarking3(marking.getFileTime());
            result.setMarking4(marking.getFilePlace());
        }else if(marking.getEventType().equals("设施启用")){
            result.setMarking1(marking.getConstructor());
            result.setMarking2(marking.getBuildingName());
            result.setMarking3(marking.getStartingTime());
            result.setMarking4(marking.getBuildingPlace());
        }else {
            result.setMarking1(marking.getHolder());
            result.setMarking2(marking.getActivityName());
            result.setMarking3(marking.getActivityPlace());
            result.setMarking4(marking.getAvtivityTime());
        }
        return result;
    }

    public List<DoneWorkResult> listAllMarkedText(String leaderName){

        List<DoneWork> doneWorks=textMapper.selectMarkedText(leaderName);

        List<DoneWorkResult> results = new ArrayList<>();
        for(DoneWork doneWork : doneWorks){
            DoneWorkResult doneWorkResult = new DoneWorkResult();
            doneWorkResult.setContent(doneWork.getContent());
            doneWorkResult.setLeader(doneWork.getLeader());
            doneWorkResult.setTextId(doneWork.getTextId());
            doneWorkResult.setTitle(doneWork.getTitle());
            doneWorkResult.setMarkingId(doneWork.getMarking().getMarkingId());
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

    public List<text> selectLeaderText(String leader){
        return textMapper.selectLeaderText(leader);
    }

    public  List<text> selectByK(Double K){
        return  textMapper.selectByK(K);
    }

    public DoneWorkResult selectByTextAndUser(String name,Integer textId){
        Integer userId=userMapper.selectUserId(name);
        DoneWork doneWork = textMapper.selectByTextAndUser(userId, textId);
        DoneWorkResult doneWorkResult = new DoneWorkResult();
        doneWorkResult.setContent(doneWork.getContent());
        doneWorkResult.setTextId(doneWork.getTextId());
        doneWorkResult.setTitle(doneWork.getTitle());
        doneWorkResult.setMarkingId(doneWork.getMarking().getMarkingId());
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
        return doneWorkResult;

    }

}


