package com.zt;

import com.zt.dao.UserMapper;
import com.zt.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class YingxueZtApplicationTests {
@Autowired
private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = new User();
        user.setName("admin");
        User admin = userMapper.selectOne(user);
        System.out.println(admin);
    }

}
