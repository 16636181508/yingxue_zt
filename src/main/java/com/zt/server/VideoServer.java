package com.zt.server;

import com.zt.entity.Video;
import com.zt.po.VideoPo;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

public interface VideoServer {
    //分页
    HashMap<String, Object> queryBypage(Integer page, Integer rows);
   //添加
    String add(Video video);
    void uploadVdieos(MultipartFile videoPath, String id, HttpServletRequest request);
    //删除
    void delete(Video video);
    //修改
    void update(Video video);
    void uploadVdieosAliyun(MultipartFile videoPath, String id, HttpServletRequest request);
    List<VideoPo> queryByReleaseTime();
}
