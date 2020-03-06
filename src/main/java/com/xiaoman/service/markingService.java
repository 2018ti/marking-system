package com.xiaoman.service;

import com.xiaoman.dao.User;
import com.xiaoman.dao.group;
import com.xiaoman.dao.marking;
import com.xiaoman.dao.text;
import com.xiaoman.mapper.UserMapper;
import com.xiaoman.mapper.groupMapper;
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
    groupMapper groupMapper;

    @Autowired
    agreementService agreementService;


    public Map<String, String> insertEventingRecord(String trigger, String participant1, String participant2, String time, String place, Integer userId, Integer textId, String role,Integer markingId,Integer groupId) {
        HashMap<String, String> result = new HashMap<>();
        text text = new text();
        text.setTextId(textId);
        if (role.equals("用户组长")) {
            System.out.println("用户组长哈");
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
            System.out.println("普通用户哈");
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
                marking.setMarkingId(markingId);
                markingMapper.updateByPrimaryKeySelective(marking);
                result.put("msg","更新成功");
            }else {
                markingMapper.insertSelective(marking);
                result.put("msg", "标记成功");
            }
            //小组成员都完成标记时进行标注一致性判断(更新或者插入都需要判断)
            if (markingMapper.countMarkingRecordByTextId(textId) == userMapper.countGroupMember(userId) - 1) {
                System.out.println("都完成标注了哈");
                group group = groupMapper.selectByPrimaryKey(groupId);
                User leader = userMapper.selectByname(group.getLeader());

                //若已被用户组长标记 则不更新标注一致性
                if(markingMapper.selectByUserIdAndText(leader.getId(),textId)!=null){
                    return result;
                }
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


    public Map<String, String> insertFileRecord(String trigger, String signatory, String file, String time, String place, Integer userId, Integer textId, String role,Integer markingId,Integer groupId) {
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
            marking.setSignatory(signatory);
            marking.setTextId(textId);
            marking.setUserId(userId);
            marking.setEventType("签署文件");
            System.out.println(marking);
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
            marking.setSignatory(signatory);
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
                group group = groupMapper.selectByPrimaryKey(groupId);
                User leader = userMapper.selectByname(group.getLeader());

                //若已被用户组长标记 则不更新标注一致性
                if(markingMapper.selectByUserIdAndText(leader.getId(),textId)!=null){
                    return result;
                }
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

    public Map<String, String> insertBuildingRecord(String trigger, String constructor, String building_name, String starting_time, String building_place, Integer userId, Integer textId, String role,Integer markingId,Integer groupId) {
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
            marking.setStartingTime(starting_time);
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
            marking.setStartingTime(starting_time);
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
                group group = groupMapper.selectByPrimaryKey(groupId);
                User leader = userMapper.selectByname(group.getLeader());

                //若已被用户组长标记 则不更新标注一致性
                if(markingMapper.selectByUserIdAndText(leader.getId(),textId)!=null){
                    return result;
                }

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

    public Map<String, String> insertActivityRecord(String trigger, String holder, String activity_name, String activity_time, String activity_place, Integer userId, Integer textId, String role,Integer markingId,Integer groupId) {
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
            marking.setHolder(holder);
            marking.setActivityName(activity_name);
            marking.setActivityPlace(activity_place);
            marking.setAvtivityTime(activity_time);
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
            marking.setAvtivityTime(activity_time);
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
                group group = groupMapper.selectByPrimaryKey(groupId);
                User leader = userMapper.selectByname(group.getLeader());

                //若已被用户组长标记 则不更新标注一致性
                if(markingMapper.selectByUserIdAndText(leader.getId(),textId)!=null){
                    return result;
                }

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

