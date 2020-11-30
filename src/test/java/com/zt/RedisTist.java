package com.zt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTist {
    @Resource
    RedisTemplate redisTemplate;

    @Test
    public void query(){
         redisTemplate.opsForValue().set("age","18");

    }


    @Test
    public void p4(){
        Object age = redisTemplate.opsForValue().get("age");
        System.out.println(age);

    }
}
