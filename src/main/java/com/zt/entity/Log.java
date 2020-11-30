package com.zt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "yx_log")
public class Log {
    private String id;

    private String name;

    private Date time;

    private String options;

    private String status;


}