package com.zt.dao;

import com.zt.entity.Auser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuserDao {
    //分页查询
    List<Auser> queryBypage(@Param("begin") Integer begin, @Param("end") Integer end);
    //计算总条数
    Integer count();
    //修改用户状态
    void update(Auser auser);
    //查所有
    List<Auser> finAlly();
}
