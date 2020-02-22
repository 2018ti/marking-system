package com.xiaoman.controller;


import com.xiaoman.utils.xmlUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class XmlFileController {
    @GetMapping("/download")
    public void downLoadText(@RequestParam(name="eventType")String eventType, @RequestParam(name="trigger",required = false)String trigger, @RequestParam(name="marking1",required = false)String makring1,
                             @RequestParam(name="marking2",required = false)String marking2, @RequestParam(name="marking3",required = false)String marking3
                            , @RequestParam(name="marking4",required = false)String marking4, @RequestParam(name="textId",required = false)Integer textId, @RequestParam(name="markingId",required = false)Integer markingId, @RequestParam(name="content",required = false)String content, HttpServletResponse response)throws Exception{
        String fileurl = xmlUtil.TextToXml(eventType, trigger, makring1, marking2, marking3, marking4, textId, markingId,content);
        File file=new File(fileurl);

        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" +   java.net.URLEncoder.encode(fileurl,"UTF-8"));
            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1){
                    os.write(buffer);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            try {
                bis.close();
                fis.close();
                file.delete();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
