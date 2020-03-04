package com.xiaoman.controller;

import com.xiaoman.dao.User;
import com.xiaoman.service.markingService;
import com.xiaoman.service.textService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/marking")
public class markingController {
    @Autowired
    markingService markingService;
    @Autowired
    textService textService;

    @PostMapping("/insertEventMarking")
    public Map<String,String> insertEvent(@RequestParam("trigger")String trigger, @RequestParam("participant1")String participant1,
                           @RequestParam("participant2")String participant2, @RequestParam("time")String time,
                           @RequestParam("place")String place, @RequestParam("textId")Integer textId,@RequestParam(value = "markingId",required = false)Integer markingId,
                           HttpServletRequest request)
    {
        System.out.println("进入");
        User user = (User)request.getSession().getAttribute("user");
        Map<String, String> result = markingService.insertEventingRecord(trigger, participant1, participant2, time, place, user.getId(), textId,user.getRole(),markingId);
        return result;
    }

    @PostMapping("/insertFileMarking")
    public Map<String,String> insertFile(@RequestParam("trigger")String trigger,@RequestParam("signatory")String signatory,
                                         @RequestParam("file")String file,@RequestParam("time")String time,@RequestParam("place")String place,
                                         @RequestParam("textId")Integer textId,@RequestParam(value = "markingId",required = false)Integer markingId,HttpServletRequest request)
    {
        User user=(User)request.getSession().getAttribute("user");
        Map<String,String> result=markingService.insertFileRecord(trigger,signatory,file,time,place,user.getId(),textId,user.getRole(),markingId);
        return result;
    }

    @PostMapping("/insertBuildMarking")
    public Map<String,String> insertBuilding(@RequestParam("trigger")String trigger,@RequestParam("constructor")String constructor,
                                         @RequestParam("buildingName")String buildingName,@RequestParam("time")String time,@RequestParam("place")String place,
                                         @RequestParam("textId")Integer textId,@RequestParam(value = "markingId",required = false)Integer markingId,HttpServletRequest request)
    {
        User user=(User)request.getSession().getAttribute("user");
        Map<String,String> result=markingService.insertBuildingRecord(trigger,constructor,buildingName,time,place,user.getId(),textId,user.getRole(),markingId);

        return result;
    }

    @PostMapping("/insertActivityMarking")
    public Map<String,String> insertActivity(@RequestParam("trigger")String trigger,@RequestParam("holder")String holder,
                                             @RequestParam("name")String name,@RequestParam("time")String time,@RequestParam("place")String place,
                                             @RequestParam("textId")Integer textId,@RequestParam(value = "markingId",required = false)Integer markingId,HttpServletRequest request)
    {
        User user=(User)request.getSession().getAttribute("user");
        Map<String,String> result=markingService.insertActivityRecord(trigger,holder,name,time,place,user.getId(),textId,user.getRole(),markingId);
        return result;
    }
}
