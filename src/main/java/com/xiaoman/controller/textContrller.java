package com.xiaoman.controller;

import com.xiaoman.dao.User;
import com.xiaoman.dao.group;
import com.xiaoman.dao.text;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.dto.ToDoMarking;
import com.xiaoman.service.groupService;
import com.xiaoman.service.textService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class textContrller {
    @Autowired
    textService textService;

    @Autowired
    groupService groupService;

    @RequestMapping("/loadText")
    public String loadtext(@RequestParam("content")String content, @RequestParam("title")String title, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        textService.insertText(content,user.getName(),title);
        return "上传成功";
    }


    @GetMapping("/todowork2")
    public  List<ToDoMarking> test(HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
            group groupById = groupService.getGroupById(user.getGroupId());
            if(groupById==null){
                return null;
            }else{
            String leader=groupById.getLeader();
                List<ToDoMarking> toDoMarkings = textService.searchToDoMarking(user.getId(),leader);
            return toDoMarkings;
        }
    }

    @GetMapping("/listgroup")
    public List<group> listallgroup(){
        return groupService.listallGroup();
    }


    @GetMapping("/donework")
    public List<DoneWorkResult> getdonwork(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<DoneWorkResult> results = textService.ListDoneWork(user.getId());
        return results;
    }

    @GetMapping("/getTxtById")
    public text gettext(@RequestParam("textId")Integer textId){
        return textService.getTxtById(textId);
    }

    @GetMapping("/getTxtByMarkingId")
    public DoneWorkResult getmarking(@RequestParam("markingId")Integer markingId){
        return textService.getTextByMarkingId(markingId);
    }

    @GetMapping("/getTextByK")
    public List<text> getTextByK(@RequestParam("K")Double K){
        return textService.selectByK(K);
    }

    @GetMapping("/listMarkedText")
    public List<DoneWorkResult> listMarked(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        String leadername=user.getName();
        return textService.listAllMarkedText(leadername);
    }

    @GetMapping("/getByTextIdAndUser")
    public DoneWorkResult getByTextIdAndUser(@RequestParam("name")String name,@RequestParam("textId")Integer textId){
        System.out.println(name+textId);
        return textService.selectByTextAndUser(name,textId);
    }

}
