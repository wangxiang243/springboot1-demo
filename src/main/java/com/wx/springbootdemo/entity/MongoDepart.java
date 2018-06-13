package com.wx.springbootdemo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "depart")
public class MongoDepart {

    private String id;

    private String pid;

    private String code;

    private String name;

    public MongoDepart() {
    }

    public MongoDepart(String pid, String code, String name) {
        this.pid = pid;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MongoDepart{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
