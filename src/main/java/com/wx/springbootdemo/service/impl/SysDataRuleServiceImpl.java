package com.wx.springbootdemo.service.impl;

import com.wx.springbootdemo.entity.SysDataRule;
import com.wx.springbootdemo.mapper.SysDataRuleMapper;
import com.wx.springbootdemo.service.SysDataRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SysDataRuleServiceImpl implements SysDataRuleService {

    @Autowired
    private SysDataRuleMapper sysDataRuleMapper;

    @Override
    public List<SysDataRule> selectSysDataRuleByUidAndUrl(Map param) {
        return sysDataRuleMapper.selectSysDataRuleByUidAndUrl(param);
    }

}
