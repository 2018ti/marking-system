package com.xiaoman.controller;

import com.xiaoman.dao.User;
import com.xiaoman.dto.ToDoMarking;
import com.xiaoman.service.textService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class textContrller {
    @Autowired
    textService textService;

    @RequestMapping("/loadText")
    public String loadtext(@Param("content")String content, HttpServletRequest request){
        User user=(User)request.getSession().getAttribute("user");
        textService.insertText(content,user.getName());
        return "上传成功";
    }

    @RequestMapping("/ToDoWorking")
    public Map<String,Object> SearchToDoWorking(HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();
        User user=(User)request.getSession().getAttribute("user");
        List<ToDoMarking> toDoMarkings = textService.searchToDoMarking(user.getId());
        if(toDoMarkings.isEmpty()){
            result.put("msg","0");
            return result;
        }else
            result.put("msg",toDoMarkings);
          return result;
    }
}
