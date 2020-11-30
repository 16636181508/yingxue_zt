package com.zt.server;

import java.util.HashMap;

public interface LogServer {
    //查所有
    //分页
    HashMap<String, Object> queryBypage(Integer page, Integer rows);
}
