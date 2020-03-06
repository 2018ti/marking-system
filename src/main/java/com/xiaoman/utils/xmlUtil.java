package com.xiaoman.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;

public class xmlUtil {
    public static String TextToXml(String eventType,String trigger,String marking1,String marking2,String marking3,String marking4,Integer textId,Integer markingId,String content,String title) throws IOException {
        System.out.println(content);
        Date date = new Date();
        String filename=title+".xml";
        if(marking1!=null){
            int marking1Start=content.indexOf(marking1)+1;
            int marking1End=marking1.length()-1+marking1Start;
        }
        if(marking2!=null){
            int marking2Start=content.indexOf(marking2)+1;
            int marking2End=marking1.length()-1+marking2Start;
        }
        if(marking2!=null){
            int marking2Start=content.indexOf(marking2)+1;
            int marking2End=marking1.length()-1+marking2Start;
        }
        if(marking2!=null){
            int marking2Start=content.indexOf(marking2)+1;
            int marking2End=marking1.length()-1+marking2Start;
        }
        Document dom= DocumentHelper.createDocument();
        Element DocumentID=dom.addElement("DocumentID");
        Element ID = DocumentID.addAttribute("ID",String.valueOf(textId));
        Element event = DocumentID.addElement("event");
        event.addAttribute("ID",String.valueOf(markingId));
        event.addAttribute("TYPE",eventType);
        Element event_trigger = event.addElement("event_trigger");
         event_trigger.addAttribute("START",String.valueOf(content.indexOf(trigger)+1));
         event_trigger.addAttribute("END",String.valueOf(content.indexOf((trigger))+trigger.length()));
         event_trigger.addText(trigger);
         if(marking1!=null){
            int marking1Start=content.indexOf(marking1)+1;
            int marking1End=marking1.length()-1+marking1Start;
            Element event_argument1 = event.addElement("event_argument");
            event_argument1.addAttribute("START",String.valueOf(marking1Start));
            event_argument1.addAttribute("END",String.valueOf(marking1End));
            if(eventType.equals("会见会谈")){
                event_argument1.addAttribute("ROLE","参与方1");
            }else if(eventType.equals("签署文件")){
                event_argument1.addAttribute("ROLE","签署方");
            }else  if(eventType.equals("设施启用")){
                event_argument1.addAttribute("ROLE","设施修建方");
            }else
                event_argument1.addAttribute("ROLE","举办方");
            event_argument1.addText(marking1);
        }
        if(marking2!=null){
            int marking2Start=content.indexOf(marking2)+1;
            int marking2End=marking2.length()-1+marking2Start;
            Element event_argument2 = event.addElement("event_argument2");
            event_argument2.addAttribute("START",String.valueOf(marking2Start));
            event_argument2.addAttribute("END",String.valueOf(marking2End));
            if(eventType.equals("会见会谈")){
                event_argument2.addAttribute("ROLE","参与方2");
            }else if(eventType.equals("签署文件")){
                event_argument2.addAttribute("ROLE","文件");
            }else  if(eventType.equals("设施启用")){
                event_argument2.addAttribute("ROLE","设施名称");
            }else
                event_argument2.addAttribute("ROLE","活动名称");
            event_argument2.addText(marking2);
        }
        if(marking3!=null){
            int marking3Start=content.indexOf(marking3)+1;
            int marking3End=marking3.length()-1+marking3Start;
            Element event_argument3 = event.addElement("event_argument");
            event_argument3.addAttribute("START",String.valueOf(marking3Start));
            event_argument3.addAttribute("END",String.valueOf(marking3End));
            if(eventType.equals("会见会谈")){
                event_argument3.addAttribute("ROLE","时间");
            }else if(eventType.equals("签署文件")){
                event_argument3.addAttribute("ROLE","签署时间");
            }else  if(eventType.equals("设施启用")){
                event_argument3.addAttribute("ROLE","启用时间");
            }else
                event_argument3.addAttribute("ROLE","活动地点");
            event_argument3.addText(marking3);
        }
        if(marking4!=null){
            int marking4Start=content.indexOf(marking4)+1;
            int marking4End=marking4.length()-1+marking4Start;
            Element event_argument4 = event.addElement("event_argument");
            event_argument4.addAttribute("START",String.valueOf(marking4Start));
            event_argument4.addAttribute("END",String.valueOf(marking4End));
            if(eventType.equals("会见会谈")){
                event_argument4.addAttribute("ROLE","地点");
            }else if(eventType.equals("签署文件")){
                event_argument4.addAttribute("ROLE","签署地点");
            }else  if(eventType.equals("设施启用")){
                event_argument4.addAttribute("ROLE","设施地点");
            }else
                event_argument4.addAttribute("ROLE","活动时间");
            event_argument4.addText(marking4);
        }

        OutputFormat format = OutputFormat.createPrettyPrint();//缩减型格式
        format.setEncoding("UTF-8");//设置编码
        format.setTrimText(false);
        Writer fileWriter = new FileWriter(filename);
        //dom4j提供了专门写入文件的对象XMLWriter
        XMLWriter xmlWriter = new XMLWriter(fileWriter,format);
        xmlWriter.write(dom);
        xmlWriter.flush();
        xmlWriter.close();
        return filename;
    }

}
