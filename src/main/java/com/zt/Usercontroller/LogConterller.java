package com.zt.Usercontroller;

import com.zt.server.LogServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
@RequestMapping("log")
public class LogConterller {
    @Resource
    private LogServer logServer;

    @RequestMapping("findAll")
    @ResponseBody
    public HashMap<String,Object> findAll(Integer page, Integer rows){

        return logServer.queryBypage(page,rows);
    }

}
