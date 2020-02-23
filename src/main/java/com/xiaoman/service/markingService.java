package com.xiaoman.service;

import com.xiaoman.dao.marking;
import com.xiaoman.dao.text;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.markingMapper;
import com.xiaoman.mapper.textMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class markingService {
    @Autowired
    markingMapper markingMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    textMapper textMapper;

    @Autowired
    agreementService agreementService;


    public Map<String,String> insertEventingRecord(String trigger, String participant1, String participant2, String time, String place, Integer userId, Integer textId){
        HashMap<String, String> result = new HashMap<>();

        if(markingMapper.selectByUserIdAndText(userId,textId)!=null){
            result.put("msg","该用户已经标记过该文章");
            return result;
        }
        marking marking = new marking();
        marking.setTrigger(trigger);
        marking.setParticipant1(participant1);
        marking.setParticipant2(participant2);
        marking.setMeetingTime(time);
        marking.setMeetingPlace(place);
        marking.setTextId(textId);
        marking.setUserId(userId);
        marking.setEventType("会见会谈");
        Integer markingId=markingMapper.insertSelective(marking);
        result.put("msg","标记成功");
        //小组成员都完成标记时进行标注一致性判断
        if(markingMapper.countMarkingRecordByTextId(textId)==userMapper.countGroupMember(userId)){
            double agree = agreementService.calAgreement(textId);
            text text = new text();
            if(agree==1){
                text.setAgreeRate(1.0);
                text.setMarkingId(markingId);
                textMapper.updateByPrimaryKey(text);
            }else{
                text.setAgreeRate(agree);
                textMapper.updateByPrimaryKey(text);
            }
        }

        return result;
    }

    public Map<String,String> insertFileRecord(String trigger, String signatory,String file,String time,String place,Integer userId,Integer textId){
        HashMap<String, String> result = new HashMap<>();

        if(markingMapper.selectByUserIdAndText(userId,textId)!=null){
            result.put("msg","该用户已经标记过该文章");
            return result;
        }
        marking marking = new marking();
        marking.setTrigger(trigger);
        marking.setFile(file);
        marking.setFileTime(time);
        marking.setFilePlace(place);
        marking.setTextId(textId);
        marking.setUserId(userId);
        marking.setEventType("签署文件");
        markingMapper.insertSelective(marking);
        result.put("msg","标记成功");
        return result;
    }
    public Map<String,String> insertBuildingRecord(String trigger, String constructor,String building_name,String starting_time,String building_place,Integer userId,Integer textId){
        HashMap<String, String> result = new HashMap<>();

        if(markingMapper.selectByUserIdAndText(userId,textId)!=null){
            result.put("msg","该用户已经标记过该文章");
            return result;
        }
        marking marking = new marking();
        marking.setTrigger(trigger);
        marking.setConstructor(constructor);
        marking.setBuildingName(building_name);
        marking.setBuildingPlace(building_place);
        marking.setTextId(textId);
        marking.setUserId(userId);
        marking.setEventType("设施启用");
        markingMapper.insertSelective(marking);
        result.put("msg","标记成功");
        return result;
    }
    public Map<String,String> insertActivityRecord(String trigger, String holder,String activity_name,String activity_time,String activity_place,Integer userId,Integer textId){
        HashMap<String, String> result = new HashMap<>();

        if(markingMapper.selectByUserIdAndText(userId,textId)!=null){
            result.put("msg","该用户已经标记过该文章");
            return result;
        }
        marking marking = new marking();
        marking.setTrigger(trigger);
        marking.setActivityName(activity_name);
        marking.setHolder(holder);
        marking.setActivityName(activity_name);
        marking.setActivityPlace(activity_place);
        marking.setTextId(textId);
        marking.setUserId(userId);
        marking.setEventType("举行活动");
        markingMapper.insertSelective(marking);
        result.put("msg","标记成功");
        return result;
    }

}
