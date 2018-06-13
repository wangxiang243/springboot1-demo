package com.wx.springbootdemo.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "role")
public class MongoRole {

    private String id;

    private String code;

    private String describe;

    public MongoRole() {
    }

    public MongoRole(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "MongoRole{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }
}
