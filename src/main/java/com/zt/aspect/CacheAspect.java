package com.zt.aspect;


import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@Configuration     //被@Configuration 修饰的类表示这事一个容器
@Aspect             //表示这是一个切面
public class CacheAspect {

    @Resource
    RedisTemplate redisTemplate;


    @Resource
    StringRedisTemplate stringRedisTemplate;

    //添加缓存
    //@Around("execution(* com.zt.server.serverImpl.*.query*(..))")
    @Around("@annotation(com.zt.annotcation.AddCache)")
    public Object addCache(ProceedingJoinPoint proceedingJoinPoint){


        System.out.println("环绕通知");
        StringBuilder sb = new StringBuilder();
        //获取类名称
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //获取方法名称
        String methodName = proceedingJoinPoint.getSignature().getName();
        System.out.println(methodName);
        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        //遍历
        for (Object arg : args) {
            sb.append(arg);
        }
        String key = sb.toString();
        //判断key是否存在
        HashOperations opsForHash = redisTemplate.opsForHash();
        Boolean aBoolean = opsForHash.hasKey(className, key);

        Object result =null;

        if (aBoolean){
            //key存在
             result = opsForHash.get(className,key);

            System.out.println("在redis中获取数据");
            //在redis中获取数据

        }else {
            //key不存在 没有缓存数据放行方法
            try {
                result = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }

            //加入缓存 放入redis
            opsForHash.put(className,key,result);
            System.out.println("加入缓存");
        }
        return result;
    }



    //清楚缓存
    //com.baizhi.serviceImpl.UserServiceImpl.queryByPage110
    //com.baizhi.serviceImpl.UserServiceImpl.queryByPage15
    //增删改执行成功之后清楚缓存
    //String,String
    // String  hash<key,value>
    /*
     * KEY,key,value
     * com.baizhi.serviceImpl.UserServiceImpl
     *       queryByPage15  数据
     *       queryByPage110 数据
     *
     * */

    // @Around("@annotation(com.zt.annotcation.AddCache)")
    @After("@annotation(com.zt.annotcation.DelCache)")
    public void delCache(JoinPoint joinpoint){
        System.out.println("后置通知");
        //获取类的权限类名
        String name = joinpoint.getTarget().getClass().getName();
        //清楚缓存
        redisTemplate.delete(name);
    }


}
