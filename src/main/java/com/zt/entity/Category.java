package com.zt.entity;

import java.io.Serializable;

public class Category implements Serializable {
    private String id;
    private String cateName;//名称
    private String levels; //级别
    private String parentId; //父id

    public Category(String id, String cateName, String levels, String parentId) {
        this.id = id;
        this.cateName = cateName;
        this.levels = levels;
        this.parentId = parentId;
    }

    public Category() {
        super();
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", cateName='" + cateName + '\'' +
                ", levels='" + levels + '\'' +
                ", parentId='" + parentId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

}
