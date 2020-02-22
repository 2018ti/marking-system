package com.xiaoman.service;

import com.xiaoman.dao.marking;
import com.xiaoman.dto.agreementMarking;
import com.xiaoman.mapper.markingMapper;
import com.xiaoman.utils.markingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class agreementService {
    @Autowired
    markingMapper markingMapper;

        //字符串比较时其中一个可能为null 进行处理
        boolean compare(String str1, String str2) {
            return ((str1 == str2) || (str1 != null && str1.equals(str2)));
        }

    public double calAgreement(Integer textId){

        List<marking> markings = markingMapper.selectAllMarkingByTextId(textId);
        ArrayList<agreementMarking> agreementMarkings = new ArrayList<agreementMarking>();//用来存储该文章标记的结果
        if(markings.size()>0){
            int markingNum = markings.size();
            for(marking marking:markings){  //遍历文章标记并添加到结果集中
                agreementMarking agreementMarking = markingUtils.getNotNullMarking(marking);
                agreementMarkings.add(agreementMarking);
            }
        }else
            return 0;
        //取出标记结果集并进行计算标注一致性
        int agreeNum=countAgreeNum(agreementMarkings);
        double result= agreeNum/5.0;
        return result;
    }
    public int countAgreeNum(ArrayList<agreementMarking> agreementMarkings){
        int count=0;
        int i=0;
        for(;i<agreementMarkings.size()-1;i++){
            if(!compare(agreementMarkings.get(i).getMarking1(),agreementMarkings.get(i+1).getMarking1())){
                break;
            }else
                continue;
        }
        if(i==agreementMarkings.size()-1){
            count++;
        }
        i=0;
        for(;i<agreementMarkings.size()-1;i++){
            if(!compare(agreementMarkings.get(i).getMarking2(),agreementMarkings.get(i+1).getMarking2())){
                break;
            }else
                continue;
        }
        if(i==agreementMarkings.size()-1){
            count++;
        }
        i=0;
        for(;i<agreementMarkings.size()-1;i++){
            if(!compare(agreementMarkings.get(i).getMarking3(),agreementMarkings.get(i+1).getMarking3())){
                break;
            }else
                continue;
        }
        if(i==agreementMarkings.size()-1){
            count++;
        }
        i=0;
        for(;i<agreementMarkings.size()-1;i++){
            if(!compare(agreementMarkings.get(i).getMarking4(),agreementMarkings.get(i+1).getMarking4())){
                break;
            }else
                continue;
        }
        if(i==agreementMarkings.size()-1){
            count++;
        }
        i=0;
        for(;i<agreementMarkings.size()-1;i++){
            if(!compare(agreementMarkings.get(i).getTrigger(),agreementMarkings.get(i+1).getTrigger())){
                break;
            }else
                continue;
        }
        if(i==agreementMarkings.size()-1){
            count++;
        }
        i=0;
        return count;
    }
}
