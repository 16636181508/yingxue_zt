package com.zt.server.serverImpl;

import com.zt.dao.LogMapper;
import com.zt.entity.Log;
import com.zt.entity.LogExample;
import com.zt.server.LogServer;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class LogServerImpl implements LogServer {
    @Resource
    private LogMapper logMapper;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public HashMap<String, Object> queryBypage(Integer page, Integer rows) {
        //获取map集合n
        HashMap<String, Object> map = new HashMap<>();
        //设置当前页
        map.put("page",page);
        //设置条件
        LogExample example = new LogExample();
        //设置分页参数
        RowBounds rowBounds = new RowBounds((page - 1) * rows, rows);
        //分页查询数据
        List<Log> logs = logMapper.selectByExampleAndRowBounds(example, rowBounds);
        //设置分页数据
        map.put("rows",logs);
        //查询总条数
        int count = logMapper.selectCountByExample(example);
        //设置总条数
        map.put("records",count);
        //设置总页数
        Integer total=count%rows==0?count/rows:count/rows+1;
        map.put("total",total);
        return map;
    }
}
