package com.wx.springbootdemo.entity;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class MongoUser {

    private String id;
    private String _class;
    private String name;
    private String password;
    private String createtime;
    private String uid;

    public MongoUser() {
    }

    public MongoUser(String _class, String name, String password, String createtime, String uid) {
        this._class = _class;
        this.name = name;
        this.password = password;
        this.createtime = createtime;
        this.uid = uid;
    }

    public MongoUser(String _class, String name, String password, String createtime, String uid, MongoDepart mongoDepart, List<MongoRole> mongoRoleList) {
        this._class = _class;
        this.name = name;
        this.password = password;
        this.createtime = createtime;
        this.uid = uid;
        this.mongoDepart = mongoDepart;
        this.mongoRoleList = mongoRoleList;
    }

    @DBRef
    private MongoDepart mongoDepart;

    @DBRef
    private List<MongoRole> mongoRoleList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public MongoDepart getMongoDepart() {
        return mongoDepart;
    }

    public void setMongoDepart(MongoDepart mongoDepart) {
        this.mongoDepart = mongoDepart;
    }

    public List<MongoRole> getMongoRoleList() {
        return mongoRoleList;
    }

    public void setMongoRoleList(List<MongoRole> mongoRoleList) {
        this.mongoRoleList = mongoRoleList;
    }

    @Override
    public String toString() {
        return "MongoUser{" +
                "id='" + id + '\'' +
                ", _class='" + _class + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", createtime='" + createtime + '\'' +
                ", uid='" + uid + '\'' +
                ", mongoDepart=" + mongoDepart +
                ", mongoRoleList=" + mongoRoleList +
                '}';
    }
}
