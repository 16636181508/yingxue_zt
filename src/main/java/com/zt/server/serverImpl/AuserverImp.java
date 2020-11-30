package com.zt.server.serverImpl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.zt.annotcation.DelCache;
import com.zt.dao.AuserDao;
import com.zt.entity.Auser;
import com.zt.server.AuserServer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class AuserverImp implements AuserServer {
    @Resource
    private AuserDao auserDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Auser> queryBy(Integer page, Integer rows) {
        Integer begin =(page-1)*rows;
        Integer ben = page*rows;
        return auserDao.queryBypage(begin,ben);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer count() {
        return auserDao.count();
    }

    @Override
    @DelCache
    //修改用户状态
    public void update(Auser auser) {
        System.out.println("收集到的数据"+auser);
        //判断用户状态
        if (auser.getFreeze().equals("1"))auser.setFreeze("0");
        else auser.setFreeze("1");
        //调用事务
        auserDao.update(auser);
    }

    @Override
    public void finAlly() {
        //调用查所有
        List<Auser> ausers = auserDao.finAlly();
        //导出设置的参数
        ExportParams params = new ExportParams("用户表","用户信息");
        //导出工具 参数：导出的参数，对应的实体类 导出集合
        //导出工具   参数:导出的参数,对应的实体类,导出的集合
        Workbook workbook = ExcelExportUtil.exportExcel(params,Auser.class,ausers);

        //导出
        try {
            workbook.write(new FileOutputStream(new File("D:\\Users.xls")));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
