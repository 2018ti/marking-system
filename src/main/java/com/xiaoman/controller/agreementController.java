package com.xiaoman.controller;

import com.xiaoman.dao.User;
import com.xiaoman.dto.DoneWorkResult;
import com.xiaoman.dto.agreementMarking;
import com.xiaoman.service.textService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class agreementController {
    @Autowired
    textService textService;

    @GetMapping("/searchMarked")
    public List<DoneWorkResult> list(HttpServletRequest httpServletRequest){
        System.out.println("-----");
        User user = (User)httpServletRequest.getSession().getAttribute("user");
        return textService.listAllMarkedText(user.getName());
    }

}
