package com.zt.Usercontroller;

import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.zt.entity.Auser;
import com.zt.server.AuserServer;
import com.zt.util.AliyunUtil;
import com.zt.util.ImageCodeUtil;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("auser")
public class AuserConterller {

    @Resource
    private AuserServer auserServer;
    //分页查询
    @RequestMapping("queryBy")
    public HashMap<Object, Object> queryBy(Integer page,Integer rows){
        //调用分页查询
        List<Auser> ausers = auserServer.queryBy(page,rows);
        //查询总条数
        Integer count = auserServer.count();
        //计算共多少页
        Integer total=count%rows==0? count /rows :(count/rows)+1;
        //获取map集合
        HashMap<Object, Object> map = new HashMap<>();
        //将数据放入map集合
        map.put("page",page);
        map.put("total",total);
        map.put("records",count);//总页数
        map.put("rows",ausers);
        return map;
    }
    //修改用户状态
    @RequestMapping("update")
    @ResponseBody
    public HashMap<String , String> update(Auser auser){
    //获取map集合
        HashMap<String , String> map = new HashMap<>();
        try{
            //调用事务
            auserServer.update(auser);
            map.put("message","修改成功");
            map.put("status","200");
        }catch (Exception e){
            e.printStackTrace();
            map.put("message","修改失败");
            map.put("status","201");
        }
        return map;
    }



    //发送短信验证
    @RequestMapping("phoneCode")
    @ResponseBody
    public HashMap<String,Object> phoneCode(String phone){
        String phoneMsg=null;
        HashMap<String,Object> map = null;
        try {
            //获取随机验证码
            String code = ImageCodeUtil.getSecurityCode();
            System.out.println("获取到的验证码为"+code);
            //调用工具类发送验证码
            phoneMsg = AliyunUtil.sendPhoneMsg(phone,code);
            System.out.println("获取到的手机号"+phoneMsg);
            map = new HashMap<>();
            map.put("message",phoneMsg);
            map.put("start",200);

        }catch (Exception e){
            e.printStackTrace();
            map.put("message",phoneMsg);
            map.put("start",201);
        }
            return map;
    }


    //表格导出
    @RequestMapping("exce")
    @ResponseBody
    public HashMap<String,String> Exce(){
        //获取map
        HashMap<String,String> map = new HashMap<>();
        try {
            auserServer.finAlly();
            map.put("message","导入成功");
            map.put("status","200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message","导入失败");
            map.put("status","404");
        }
        return map;
    }

}
