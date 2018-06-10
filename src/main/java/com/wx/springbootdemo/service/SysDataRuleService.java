package com.wx.springbootdemo.service;

import com.wx.springbootdemo.entity.SysDataRule;

import java.util.List;
import java.util.Map;

public interface SysDataRuleService {

    List<SysDataRule> selectSysDataRuleByUidAndUrl(Map param);

}
