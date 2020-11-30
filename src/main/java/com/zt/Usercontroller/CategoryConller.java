package com.zt.Usercontroller;

import com.alibaba.druid.util.StringUtils;
import com.zt.entity.Category;
import com.zt.server.CategoryServer;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryConller {

    @Resource
    private CategoryServer categoryServer;
    //查所有分页展示
    @RequestMapping("queryBypage")
    public HashMap<String, Object> queryBypage(Integer page,Integer rows){
        //调用业务
        List<Category> categories = categoryServer.queryBypage(page,rows);
        //查询总条数
        Integer count = categoryServer.count();
        //计算一共多少页
        Integer total =count%rows==0?count/rows :(count/rows)+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("total",total);
        map.put("records",count);
        map.put("rows",categories);
        return map;
    }
    //增删改查
    @RequestMapping("edit")
    @ResponseBody
    public HashMap<String , Object> edit(Category category,String oper,String id){
        HashMap<String, Object> map = new HashMap<>();
        //获取的数据
        System.out.println("接受的数据"+category);
        //判断用户添加方法
        if (StringUtils.equals("add",oper)){
            //调用添加方法
            categoryServer.inste(category);
        }
        //判断修改
        if (StringUtils.equals("edit",oper)){
            //调用修改
            categoryServer.update(category);
        }
        //判断删除
        if (StringUtils.equals("del",oper)){
            //调用删除方法
            map = categoryServer.daleteOne(id);

            System.out.println(map);
        }
        return map;

    }






    //分类查询级别
    @RequestMapping("queryFile")
    @ResponseBody
    public HashMap<String, Object> queryFile(Integer page,Integer rows,String id){
        //调用查方法
        List<Category> categories = categoryServer.queryFile(page, rows,id);
        //调用查总条数
        Integer counte = categoryServer.counte(id);
        //计算页数
        Integer total=counte%rows==0? counte/rows :(counte/rows)+1;
        //获取map集合
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("total",total);
        map.put("records",counte);
        map.put("rows",categories);
        System.out.println(map);
        return map;
    }



}
