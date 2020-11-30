package com.zt.server;

import com.zt.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface CategoryServer {
    //查所有分页
    List<Category> queryBypage( Integer page, Integer rows);
    //查询总条数
    Integer count();
    //添加类别
    void inste(Category category);
    //修改用户
    void update(Category category);
    //根据id删除
    HashMap<String , Object> daleteOne(String id);
    //类别查询分页
    List<Category> queryFile( Integer page, Integer rows,String id);
    Integer counte(String id);
}
