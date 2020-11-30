package com.zt;

import com.zt.dao.AuserDao;
import com.zt.entity.Auser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuserTest {

    @Resource
    private AuserDao auserDao;
    @Test
    public void query(){
        List<Auser> ausers = auserDao.queryBypage(0,1);
        for (Auser auser : ausers) {
            System.out.println(auser);
        }
    }

    @Test
    public void count(){
        Integer count = auserDao.count();
    }

    @Test
    public void select(){
        List<Auser> ausers = auserDao.finAlly();
        for (Auser auser : ausers) {
            System.out.println(auser);
        }
    }

}
