package com.zt.app;

import com.zt.common.CommonResult;
import com.zt.po.VideoPo;
import com.zt.server.VideoServer;
import com.zt.util.AliyunUtil;
import com.zt.util.ImageCodeUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("app")
public class AppController {

    @Resource
    VideoServer videoServer;


    @RequestMapping("getPhoneCode")
    public Object getPhoneCode(String phone){
        //生成随机验证码
        String message = null;

            String randomCode = ImageCodeUtil.getSecurityCode();
            System.out.println("获取到的验证码为"+randomCode);
        try {
            message = AliyunUtil.sendPhoneMsg(phone,randomCode);
            return new CommonResult().success("100",message,phone);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult().failed("104",message,phone);
        }

    }


    @RequestMapping("queryByReleaseTime")
    public Object queryByReleaseTime(){

        try {
            List<VideoPo> videoPos = videoServer.queryByReleaseTime();
            return new CommonResult().success("100","请求成功",videoPos);
        } catch (Exception e) {
            return new CommonResult().failed(null,null,null);
        }
    }
}
