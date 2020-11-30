package com.zt;

import com.zt.dao.CategoryDao;
import com.zt.entity.Category;
import com.zt.server.CategoryServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryTest {
    @Resource
    private CategoryServer categoryServer;
    @Autowired
    private CategoryDao categoryDao;


    @Test
    public void inster(){
        Category category = new Category("6","医学","1","2");
        categoryServer.inste(category);
        System.out.println("ok");
    }

    //测试dao
    @Test
    public void update(){
        Category category = new Category("5e003a88-801e-4d46-aa0e-05b015e55821","现代医学","1","2");
        categoryDao.update(category);
        System.out.println("修改成功");
    }
    @Test
    public void queryTwo(){
        List<Category> categories = categoryDao.queryFile(1, 2,"5");
        for (Category category : categories) {
            System.out.println(category);
        }
    }
    @Test
    public void count(){
        List<Category> categories = categoryDao.queryBypage(0, 10);
        categories.forEach(cate -> System.out.println(cate));
        Integer counte = categoryDao.count();
        System.out.println(counte);
    }

    @Test
    public void queryById(){
        Category category = categoryDao.queryById("20");
        System.out.println(category);
    }
}
