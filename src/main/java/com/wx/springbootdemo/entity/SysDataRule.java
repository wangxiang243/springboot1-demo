package com.wx.springbootdemo.entity;

import java.util.Date;

public class SysDataRule {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_rule.id
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_rule.data_permission_id
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    private Integer dataPermissionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_rule.rule_name
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    private String ruleName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_rule.rule_value
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    private String ruleValue;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_rule.create_time
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_data_rule.update_time
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_rule.id
     *
     * @return the value of sys_data_rule.id
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_rule.id
     *
     * @param id the value for sys_data_rule.id
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_rule.data_permission_id
     *
     * @return the value of sys_data_rule.data_permission_id
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public Integer getDataPermissionId() {
        return dataPermissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_rule.data_permission_id
     *
     * @param dataPermissionId the value for sys_data_rule.data_permission_id
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public void setDataPermissionId(Integer dataPermissionId) {
        this.dataPermissionId = dataPermissionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_rule.rule_name
     *
     * @return the value of sys_data_rule.rule_name
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public String getRuleName() {
        return ruleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_rule.rule_name
     *
     * @param ruleName the value for sys_data_rule.rule_name
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public void setRuleName(String ruleName) {
        this.ruleName = ruleName == null ? null : ruleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_rule.rule_value
     *
     * @return the value of sys_data_rule.rule_value
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public String getRuleValue() {
        return ruleValue;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_rule.rule_value
     *
     * @param ruleValue the value for sys_data_rule.rule_value
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public void setRuleValue(String ruleValue) {
        this.ruleValue = ruleValue == null ? null : ruleValue.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_rule.create_time
     *
     * @return the value of sys_data_rule.create_time
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_rule.create_time
     *
     * @param createTime the value for sys_data_rule.create_time
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_data_rule.update_time
     *
     * @return the value of sys_data_rule.update_time
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_data_rule.update_time
     *
     * @param updateTime the value for sys_data_rule.update_time
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}