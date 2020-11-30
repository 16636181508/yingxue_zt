package com.zt.dao;

import com.zt.entity.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    //查所有分页
    List<Category> queryBypage(@Param("page") Integer page,@Param("end") Integer end);
    //查询总条数
    Integer count();
    //添加类别
    void inster(Category category);
    //修改用户信息
    void update(Category category);
    //删除
    void delete(String id);
    //根据id查一个
    Category queryById(String id);
    //前台
    //展示2及类别分页查询
    List<Category> queryFile(@Param("page") Integer page,@Param("end") Integer end,@Param("id")String id);
    //查询2及类别的总条数
    Integer counte(String id);


}
