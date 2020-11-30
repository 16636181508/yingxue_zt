package com.zt.Usercontroller;

import com.alibaba.druid.util.StringUtils;
import com.zt.entity.Video;
import com.zt.server.VideoServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
@RequestMapping("video")
public class VideoConterller {
    @Resource
    private VideoServer videoServer;

    @RequestMapping("querypage")
    @ResponseBody
    public HashMap<String,Object> querypage(Integer page,Integer rows){
        return videoServer.queryBypage(page,rows);
    }

    @ResponseBody
    @RequestMapping("edit")
    public String edit(Video video ,String oper){
        String result=null;
        //判断
        if (StringUtils.equals("add",oper)){
             result = videoServer.add(video);
        }
        if (StringUtils.equals("edit",oper)){
            videoServer.update(video);
        }
        //判断删除
        if (StringUtils.equals("del",oper)){
            videoServer.delete(video);
        }
        return result;
    }

    @RequestMapping("uploadVdieo")
    public void uploadVdieo(MultipartFile videoPath,String id, HttpServletRequest request){

        videoServer.uploadVdieosAliyun(videoPath,id,request);
    }
}
