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


    public Map<String, String> insertEventingRecord(String trigger, String participant1, String participant2, String time, String place, Integer userId, Integer textId, String role,Integer markingId) {
        HashMap<String, String> result = new HashMap<>();
        text text = new text();
        text.setTextId(textId);
        if (role.equals("用户组长")) {
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                result.put("msg", "该用户已经标记过该文章");
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
            markingMapper.insertSelective(marking);
            text.setAgreeRate(1.0);
            text.setMarkingId(marking.getMarkingId());
            textMapper.updateByPrimaryKeySelective(text);
            result.put("msg", "标记成功");
            return result;
        } else {
            marking marking = new marking();
            marking.setTrigger(trigger);
            marking.setParticipant1(participant1);
            marking.setParticipant2(participant2);
            marking.setMeetingTime(time);
            marking.setMeetingPlace(place);
            marking.setTextId(textId);
            marking.setUserId(userId);
            marking.setEventType("会见会谈");
            //若用户已标记该文章 则进行更新操作
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                System.out.println("更新会见会谈");
                marking.setMarkingId(markingId);
                markingMapper.updateByPrimaryKeySelective(marking);
                result.put("msg","更新成功");
            }else {
                markingMapper.insertSelective(marking);
                result.put("msg", "标记成功");
            }
            //小组成员都完成标记时进行标注一致性判断(更新或者插入都需要判断)
            if (markingMapper.countMarkingRecordByTextId(textId) == userMapper.countGroupMember(userId) - 1) {
                System.out.println("组成员都完成标记");
                double agree = agreementService.calAgreement(textId);
                if (agree == 1) {
                    text.setAgreeRate(1.0);
                    text.setMarkingId(marking.getMarkingId());
                    textMapper.updateByPrimaryKeySelective(text);
                } else {
                    text.setAgreeRate(agree);
                    textMapper.updateByPrimaryKeySelective(text);
                }
            }
            return result;
        }
    }


    public Map<String, String> insertFileRecord(String trigger, String signatory, String file, String time, String place, Integer userId, Integer textId, String role,Integer markingId) {
        HashMap<String, String> result = new HashMap<>();
        text text = new text();
        text.setTextId(textId);
        if (role.equals("用户组长")) {
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                result.put("msg", "该用户已经标记过该文章");
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
            text.setAgreeRate(1.0);
            text.setMarkingId(marking.getMarkingId());
            textMapper.updateByPrimaryKeySelective(text);
            result.put("msg", "标记成功");
            return result;
        } else {
            marking marking = new marking();
            marking.setTrigger(trigger);
            marking.setFile(file);
            marking.setFileTime(time);
            marking.setFilePlace(place);
            marking.setTextId(textId);
            marking.setUserId(userId);
            marking.setEventType("签署文件");
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                marking.setMarkingId(markingId);
                markingMapper.updateByPrimaryKeySelective(marking);
                result.put("msg","更新成功");
            }else {
                markingMapper.insertSelective(marking);
                result.put("msg", "标记成功");
            }

            if (markingMapper.countMarkingRecordByTextId(textId) == userMapper.countGroupMember(userId) - 1) {
                System.out.println("组成员都完成标记");
                double agree = agreementService.calAgreement(textId);
                if (agree == 1) {
                    text.setAgreeRate(1.0);
                    text.setMarkingId(marking.getMarkingId());
                    textMapper.updateByPrimaryKeySelective(text);
                } else {
                    text.setAgreeRate(agree);
                    textMapper.updateByPrimaryKeySelective(text);
                }
            }
            return result;
        }
    }

    public Map<String, String> insertBuildingRecord(String trigger, String constructor, String building_name, String starting_time, String building_place, Integer userId, Integer textId, String role,Integer markingId) {
        HashMap<String, String> result = new HashMap<>();
        text text = new text();
        text.setTextId(textId);
        if (role.equals("用户组长")) {
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                result.put("msg", "该用户已经标记过该文章");
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
            text.setAgreeRate(1.0);
            text.setMarkingId(marking.getMarkingId());
            textMapper.updateByPrimaryKeySelective(text);
            result.put("msg", "标记成功");
            return result;
        } else {

            marking marking = new marking();
            marking.setTrigger(trigger);
            marking.setConstructor(constructor);
            marking.setBuildingName(building_name);
            marking.setBuildingPlace(building_place);
            marking.setTextId(textId);
            marking.setUserId(userId);
            marking.setEventType("设施启用");
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                marking.setMarkingId(markingId);
                markingMapper.updateByPrimaryKeySelective(marking);
                result.put("msg","更新成功");
            }else {
                markingMapper.insertSelective(marking);
                result.put("msg", "标记成功");
            }

            if (markingMapper.countMarkingRecordByTextId(textId) == userMapper.countGroupMember(userId) - 1) {
                System.out.println("组成员都完成标记");
                double agree = agreementService.calAgreement(textId);
                if (agree == 1) {
                    text.setAgreeRate(1.0);
                    text.setMarkingId(marking.getMarkingId());
                    textMapper.updateByPrimaryKeySelective(text);
                } else {
                    text.setAgreeRate(agree);
                    textMapper.updateByPrimaryKeySelective(text);
                }
            }
            return result;
        }
    }

    public Map<String, String> insertActivityRecord(String trigger, String holder, String activity_name, String activity_time, String activity_place, Integer userId, Integer textId, String role,Integer markingId) {
        HashMap<String, String> result = new HashMap<>();
        text text = new text();
        text.setTextId(textId);
        if (role.equals("用户组长")) {
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                result.put("msg", "该用户已经标记过该文章");
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
            text.setMarkingId(marking.getMarkingId());
            text.setAgreeRate(1.0);
            textMapper.updateByPrimaryKeySelective(text);
            result.put("msg", "标记成功");
            return result;
        } else {

            marking marking = new marking();
            marking.setTrigger(trigger);
            marking.setActivityName(activity_name);
            marking.setHolder(holder);
            marking.setActivityName(activity_name);
            marking.setActivityPlace(activity_place);
            marking.setTextId(textId);
            marking.setUserId(userId);
            marking.setEventType("举行活动");
            if (markingMapper.selectByUserIdAndText(userId, textId) != null) {
                marking.setMarkingId(markingId);
                markingMapper.updateByPrimaryKeySelective(marking);
                result.put("msg","更新成功");
            }else {
                markingMapper.insertSelective(marking);
                result.put("msg", "标记成功");
            }

            if (markingMapper.countMarkingRecordByTextId(textId) == userMapper.countGroupMember(userId) - 1) {
                System.out.println("组成员都完成标记");
                double agree = agreementService.calAgreement(textId);
                if (agree == 1) {
                    text.setAgreeRate(1.0);
                    text.setMarkingId(marking.getMarkingId());
                    textMapper.updateByPrimaryKeySelective(text);
                } else {
                    text.setAgreeRate(agree);
                    textMapper.updateByPrimaryKeySelective(text);
                }
            }
            return result;
        }
    }
}

