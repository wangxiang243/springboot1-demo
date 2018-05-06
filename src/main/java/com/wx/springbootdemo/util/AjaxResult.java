package com.wx.springbootdemo.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AjaxResult {

    public static final int FAIL_CODE = 0;
    public static final int SUCCESS_CODE = 1;

    private int code;

    private Object data;

    private String msg;

    public AjaxResult(){}

    public AjaxResult(int code, Object data, String msg){
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static final AjaxResult success(){
        return new AjaxResult(SUCCESS_CODE, null, null);
    }

    public static final AjaxResult success(Object data){
        return new AjaxResult(SUCCESS_CODE, data, null);
    }

    public static final AjaxResult success(String msg){
        return new AjaxResult(SUCCESS_CODE, null, msg);
    }

    public static final AjaxResult success(Object data, String msg){
        return new AjaxResult(SUCCESS_CODE, data, msg);
    }

    public static final AjaxResult fail(){
        return new AjaxResult(FAIL_CODE, null, null);
    }

    public static final AjaxResult fail(String msg){
        return new AjaxResult(FAIL_CODE, null, msg);
    }

    public static final AjaxResult fail(Object data, String msg){
        return new AjaxResult(FAIL_CODE, data, msg);
    }

    public static final AjaxResult fail(int code, Object data, String msg){
        return new AjaxResult(code, data, msg);
    }

    public static final Map successMap(Object data, String msg) {
        Map result = new HashMap();
        result.put("data", data);
        result.put("code", SUCCESS_CODE);
        result.put("msg", msg);
        return result;
    }

    public static final Map successMap(String msg) {
        Map result = new HashMap();
        result.put("data", null);
        result.put("code", SUCCESS_CODE);
        result.put("msg", msg);
        return result;
    }

    public static final Map failMap(Object data, String msg) {
        Map result = new HashMap();
        result.put("data", data);
        result.put("code", FAIL_CODE);
        result.put("msg", msg);
        return result;
    }

    public static final Map failMap(String msg) {
        Map result = new HashMap();
        result.put("data", null);
        result.put("code", FAIL_CODE);
        result.put("msg", msg);
        return result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess(){
        return code == SUCCESS_CODE ? true : false;
    }

    @Override
    public String toString() {
        return "AjaxUtils{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }

    public static boolean isAjaxRequest(ServletRequest request) {

        if ("XMLHttpRequest".equalsIgnoreCase(((HttpServletRequest) request).getHeader("X-Requested-With")) || "pajax".equalsIgnoreCase(((HttpServletRequest) request).getHeader("pajax"))) {
            return true;
        }else{
            return false;
        }

    }

}
