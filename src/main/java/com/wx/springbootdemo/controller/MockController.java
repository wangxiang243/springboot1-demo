package com.wx.springbootdemo.controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wx.springbootdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class MockController {

    @RequestMapping("fake_chart_data")
    public AjaxResult fake_chart_data(){

        Map result = Maps.newLinkedHashMap();
        List<Map> visitDataList = Lists.newArrayList();
        result.put("visitData", visitDataList);
        result.put("visitData2", visitDataList);
        result.put("salesData", visitDataList);
        result.put("searchData", visitDataList);
        result.put("offlineData", visitDataList);
        result.put("offlineChartData", visitDataList);
        result.put("salesTypeData", visitDataList);
        result.put("salesTypeDataOnline", visitDataList);
        result.put("salesTypeDataOffline", visitDataList);
        result.put("radarData", visitDataList);

        return AjaxResult.success(result);
    }

}
