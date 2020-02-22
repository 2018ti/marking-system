package com.xiaoman.utils;

import com.xiaoman.dao.marking;
import com.xiaoman.dto.agreementMarking;

public  class  markingUtils {

    public static agreementMarking getNotNullMarking(marking marking){
        agreementMarking agreementMarking = new agreementMarking();
        agreementMarking.setTrigger(marking.getTrigger());
        if(marking.getEventType().equals("会见会谈")){
                agreementMarking.setMarking1(marking.getParticipant1());
                agreementMarking.setMarking2(marking.getParticipant2());
                agreementMarking.setMarking3(marking.getMeetingTime());
                agreementMarking.setMarking4(marking.getMeetingPlace());
        }else if(marking.getEventType().equals("签署文件")){
            agreementMarking.setMarking1(marking.getSignatory());
            agreementMarking.setMarking2(marking.getFile());
            agreementMarking.setMarking3(marking.getFileTime());
            agreementMarking.setMarking4(marking.getFilePlace());
        }else if(marking.getEventType().equals("设施启用")){
            agreementMarking.setMarking1(marking.getConstructor());
            agreementMarking.setMarking2(marking.getBuildingName());
            agreementMarking.setMarking3(marking.getStartingTime());
            agreementMarking.setMarking4(marking.getBuildingPlace());
        }else {
            agreementMarking.setMarking1(marking.getHolder());
            agreementMarking.setMarking2(marking.getActivityName());
            agreementMarking.setMarking3(marking.getActivityPlace());
            agreementMarking.setMarking4(marking.getAvtivityTime());
        }
        return agreementMarking;
    }
}
