package com.zt.server.serverImpl;

import com.zt.dao.CategoryDao;
import com.zt.entity.Category;
import com.zt.server.CategoryServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CategoryServerImpl implements CategoryServer {
    //注入dao
    @Resource
    private CategoryDao categoryDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryBypage(Integer page, Integer rows) {
        //
        Integer begin =(page-1)*rows;
        Integer ben = page*rows;
        return categoryDao.queryBypage(begin,ben);
    }

    @Override
    public Integer count() {
        return categoryDao.count();
    }

    //用户添加
    @Override
    public void inste(Category category) {
        category.setId(UUID.randomUUID().toString());
        //调用添加方法
        //判断
        if(category.getParentId()!=null){ //1
            category.setLevels("2");
            categoryDao.inster(category);
        }
        if (category.getParentId()==null){
            category.setLevels("1");
            category.setParentId(null);
            categoryDao.inster(category);
        }

    }

    //用户修改
    @Override
    public void update(Category category) {
        //调用dao
        categoryDao.update(category);
    }

    @Override
    //删除
    public HashMap<String , Object> daleteOne(String id) {
        String message=null;
        //调用根据id查所有
//        Category category = categoryDao.queryById(id);
        //判断
//        if (category.getLevels().equals("1")){
//            //查询总条数
//
//            if (counte==0){
//                //删除类别
//                categoryDao.delete(category.getId());
//            }else {
//                message="该类别上有二级类别不能删除";
//            }
//
//        }else {
//            //如果一级类别下没有二级类别则直接删除
//            categoryDao.delete(category.getId());
//        }
        //根据id查总条数
        Integer counts = categoryDao.counte(id);
        //判断
        if(counts==0){
            categoryDao.delete(id);
            message = "删除成功";
        }else{
            message = "该类别下有对应二级类别";
        }
        HashMap<String , Object> map = new HashMap<>();
        //将错误信息存入到map集合
        map.put("message",message);

        return map;
    }


    //分类分页查询
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Category> queryFile(Integer page, Integer rows,String id) {
        //计算
        Integer begin =(page-1)*rows;
        Integer end =page*rows;
        return categoryDao.queryFile(begin,end,id);
    }

    @Override
    public Integer counte(String id) {
        return categoryDao.counte(id);
    }


}
