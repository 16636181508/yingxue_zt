package com.zt.server.serverImpl;

import com.alibaba.druid.util.StringUtils;
import com.zt.annotcation.AddCache;
import com.zt.dao.UserMapper;
import com.zt.entity.User;
import com.zt.server.UserServer;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@Transactional
public class UserServerImpl implements UserServer {
    @Resource
    private UserMapper userMapper;



    @AddCache
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public String qureyOne(User user, HttpServletRequest request,String enCode) {
        String message=null;
        User user1 = userMapper.selectOne(user);
        //获取验证码
        HttpSession session = request.getSession();
        String code1 = (String) session.getAttribute("code");
        //判断验证码
        if (code1.equals(enCode)){
            //判断账号密码
            if (user1!=null){
                if (user1.getPassword().equals(user.getPassword())){
                    message="success";
                    request.setAttribute("user",user1);
                }else{
                    message="用户名或密码错误";
                }

            }else{
                message="没有找到给用户";

            }

        }else {message="验证码错误";}


        return message;
    }


}
