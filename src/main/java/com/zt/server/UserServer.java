package com.zt.server;

import com.zt.entity.User;

import javax.servlet.http.HttpServletRequest;

public interface UserServer {
    //用户登录
    String qureyOne(User user, HttpServletRequest request,String enCode);
}
