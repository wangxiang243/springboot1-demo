package com.wx.springbootdemo.mapper;

import com.wx.springbootdemo.entity.SysPermission;

import java.util.List;

public interface SysPermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Mar 21 17:32:39 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Mar 21 17:32:39 CST 2018
     */
    int insert(SysPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Mar 21 17:32:39 CST 2018
     */
    int insertSelective(SysPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Mar 21 17:32:39 CST 2018
     */
    SysPermission selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Mar 21 17:32:39 CST 2018
     */
    int updateByPrimaryKeySelective(SysPermission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_permission
     *
     * @mbg.generated Wed Mar 21 17:32:39 CST 2018
     */
    int updateByPrimaryKey(SysPermission record);

    List<SysPermission> selectByRoleId(int roleId);
}