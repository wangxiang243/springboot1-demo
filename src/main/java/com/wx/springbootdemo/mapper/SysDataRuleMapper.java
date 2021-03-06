package com.wx.springbootdemo.mapper;

import com.wx.springbootdemo.entity.SysDataRule;

import java.util.List;
import java.util.Map;

public interface SysDataRuleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_data_rule
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_data_rule
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    int insert(SysDataRule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_data_rule
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    int insertSelective(SysDataRule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_data_rule
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    SysDataRule selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_data_rule
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    int updateByPrimaryKeySelective(SysDataRule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_data_rule
     *
     * @mbg.generated Thu Jun 07 17:26:06 CST 2018
     */
    int updateByPrimaryKey(SysDataRule record);

    /**
     * 根据uid和url查询数据权限
     * @param param
     * @return
     */
    List<SysDataRule> selectSysDataRuleByUidAndUrl(Map param);
}