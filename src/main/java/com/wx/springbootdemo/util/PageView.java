package com.wx.springbootdemo.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangxiang on 16/3/22.
 */
public class PageView<T> {

    private Integer currentPage = 1;

    private Integer pageSize = 10;

    private Integer totalPage;

    private Integer start;

    private List<T> rows;

    private Integer totalCount;

    public PageView(){}

    public PageView(Integer currentPage, Integer pageSize, List<T> rows, Integer totalCount){
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.rows = rows;
        this.totalCount = totalCount;
        this.totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount/pageSize + 1;
    }


    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStart() {
        return (currentPage - 1) * pageSize;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public static Integer getPage(Map param){
        if(param != null){
            if(param.get("currentPage") != null  && StringUtils.isNotBlank(param.get("currentPage").toString())){
                return Integer.valueOf(param.get("currentPage").toString()) <= 0 ? 1:Integer.valueOf(param.get("currentPage").toString()) ;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
    }

    public static Integer getPageSize(Map param){
        if(param != null){
            if(param.get("pageSize") != null  && StringUtils.isNotBlank(param.get("pageSize").toString())){
                return Integer.valueOf(param.get("pageSize").toString());
            }else{
                return 10;
            }
        }else{
            return 10;
        }
    }

    public static Integer getStart(Map param){
        int page = getPage(param);
        int pageSize = getPageSize(param);
        return (page - 1) * pageSize;
    }

    public static Map getPageView(Map param, int totalCount, List list){
        int currentPage = getPage(param);
        int pageSize = getPageSize(param);
        Map result = new HashMap();
        Map paginationMap = Maps.newLinkedHashMap();
        paginationMap.put("total", totalCount);
        paginationMap.put("pageSize", pageSize);
        paginationMap.put("current", currentPage);
        paginationMap.put("showSizeChanger", true);
//        PageView pageView = new PageView(currentPage,pageSize,list,totalCount);
        result.put("list", list);
        result.put("pagination", paginationMap);
        return result;
    }

    public static Map getNoPageView(List list) {
        Map result = new HashMap();
        result.put("list", list);
        result.put("pagination", false);
        return result;
    }

}
