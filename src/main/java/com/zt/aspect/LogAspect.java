package com.zt.aspect;

import com.zt.annotcation.AddLog;
import com.zt.dao.LogMapper;

import com.zt.entity.Log;
import com.zt.entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;
public class LogAspect {

    @Resource
    HttpServletRequest request;
    @Resource
    LogMapper logMapper;
    @Around("@annotation(com.zt.annotcation.AddLog)")
    public Object   addLog(ProceedingJoinPoint proceedingJoinPoint){

        //谁  时间  操作  成功
        //获取用户数据
        User admin = (User) request.getSession().getAttribute("user");


        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();

        //获取方法
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();

        //获取注解
        AddLog addLog = method.getAnnotation(AddLog.class);

        //获取注解对应的属性值
        String value = addLog.value();

        String message=null;
        Object result =null;
        //放行方法
        try {
            result = proceedingJoinPoint.proceed();

            String s = result.toString();

            message="success";
        } catch (Throwable throwable) {
            message="error";
        }
        String options=methodName+" ("+value+")";
        Log log = new Log();
        log.setId(UUID.randomUUID().toString());
        log.setName(admin.getName());
        log.setTime(new Date());
        log.setOptions(options);
        log.setStatus(message);
        logMapper.insert(log);
        return result;
    }


}
