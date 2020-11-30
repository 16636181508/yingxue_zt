package com.zt;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zt.dao")
@org.mybatis.spring.annotation.MapperScan("com.zt.dao")

public class YingxueZtApplication {
    public static void main(String[] args) {
        SpringApplication.run(YingxueZtApplication.class,args);
    }
}
