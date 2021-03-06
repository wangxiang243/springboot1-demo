package com.wx.springbootdemo.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.uid
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    private Integer uid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.username
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    private String username;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.password
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.salt
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    private String salt;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.name
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.state
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    private Byte state;

    public UserInfo() {
    }

    public UserInfo(String username, String password, String salt, String name, Byte state) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.name = name;
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.uid
     *
     * @return the value of user_info.uid
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.uid
     *
     * @param uid the value for user_info.uid
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.username
     *
     * @return the value of user_info.username
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.username
     *
     * @param username the value for user_info.username
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.password
     *
     * @return the value of user_info.password
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.password
     *
     * @param password the value for user_info.password
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.salt
     *
     * @return the value of user_info.salt
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public String getSalt() {
        return salt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.salt
     *
     * @param salt the value for user_info.salt
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.name
     *
     * @return the value of user_info.name
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.name
     *
     * @param name the value for user_info.name
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.state
     *
     * @return the value of user_info.state
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public Byte getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.state
     *
     * @param state the value for user_info.state
     *
     * @mbg.generated Wed Mar 21 17:18:49 CST 2018
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * 密码盐.
     * @return
     */
    public String getCredentialsSalt(){
        return this.username+this.salt;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                '}';
    }
}