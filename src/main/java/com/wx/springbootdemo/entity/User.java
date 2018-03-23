package com.wx.springbootdemo.entity;

import com.wx.springbootdemo.enums.UserSexEnum;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String userName;
    private String password;
    private UserSexEnum userSex;
    private String nickName;

    public User() {
        super();
    }

    public User(String userName, String password, UserSexEnum userSex) {
        super();
        this.password = password;
        this.userName = userName;
        this.userSex = userSex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passWord) {
        this.password = passWord;
    }

    public UserSexEnum getUserSex() {
        return userSex;
    }

    public void setUserSex(UserSexEnum userSex) {
        this.userSex = userSex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userSex=" + userSex +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
