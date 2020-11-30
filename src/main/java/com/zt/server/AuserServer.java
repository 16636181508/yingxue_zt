package com.zt.server;

import com.zt.entity.Auser;

import java.util.List;

public interface AuserServer {
    //分页
    List<Auser> queryBy(Integer page, Integer rows);
    //查询总条数
    Integer count();
    //修改用户状态
    void update(Auser auser);
    void finAlly();
}
