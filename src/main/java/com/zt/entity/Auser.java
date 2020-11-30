package com.zt.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

public class Auser implements Serializable {
    @Excel(name = "id")
    private String id; //id
    @Excel(name = "姓名")
    private String nikeName;//姓名
    @Excel(name = "图片",type = 2)
    private String picImg; //图片
    @Excel(name = "电话")
    private String phone;//电话号码
    @Excel(name = "简介")
    private String brief;//简介
    @Excel(name = "学分")
    private String score;//学分
    @Excel(name = "创建时间",exportFormat = "yyyy-MM-dd",importFormat = "yyyy-MM-dd")
    private Date createDate;//创建时间
    @Excel(name = "状态")
    private  String freeze; //状态

    public Auser() {
        super();
    }

    @Override
    public String toString() {
        return "Auser{" +
                "id='" + id + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", picImg='" + picImg + '\'' +
                ", phone='" + phone + '\'' +
                ", brief='" + brief + '\'' +
                ", score='" + score + '\'' +
                ", createDate=" + createDate +
                ", freeze='" + freeze + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getPicImg() {
        return picImg;
    }

    public void setPicImg(String picImg) {
        this.picImg = picImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getFreeze() {
        return freeze;
    }

    public void setFreeze(String freeze) {
        this.freeze = freeze;
    }

    public Auser(String id, String nikeName, String picImg, String phone, String brief, String score, Date createDate, String freeze) {
        this.id = id;
        this.nikeName = nikeName;
        this.picImg = picImg;
        this.phone = phone;
        this.brief = brief;
        this.score = score;
        this.createDate = createDate;
        this.freeze = freeze;
    }
}
