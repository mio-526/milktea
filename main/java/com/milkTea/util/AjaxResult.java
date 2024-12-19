package com.milkTea.util;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;

public class AjaxResult extends HashMap<String,Object> {

    private static final String CODE = "code";
    private static final String MSG = "msg";
    private static final String DATA = "data";


    public AjaxResult() {

    }

    public AjaxResult(int code, String msg,Object data) {
        super.put(CODE, code);
        super.put(MSG, msg);
        if(ObjectUtil.isNotNull(data))
            super.put(DATA, data);
    }
    public AjaxResult put(String key,Object value) {
        super.put(key, value);
        return this;
    }

    public static AjaxResult success(int code, String msg,Object data){
        return new AjaxResult(code,msg,data);
    }

    public static AjaxResult success(int code,String msg){
        return success(code,msg,null);
    }

    public static AjaxResult success(Object data){
        return success(200,"操作成功！",data);
    }

    public static AjaxResult success(String msg,Object data){
        return success(200,msg,data);
    }

    public static AjaxResult success(int code,Object data){
        return success(code,"操作成功！",data);
    }

    public static AjaxResult success(){
        return success(null);
    }

    public static AjaxResult error(int code,String msg,Object data){
        return new AjaxResult(code,msg,data);
    }

    public static AjaxResult error(int code,String msg){
        return error(code,msg,null);
    }

    public static AjaxResult error(String msg,Object data){
        return error(500,msg,data);
    }

    public static AjaxResult error(String msg){
        return error(500,msg,null);
    }

    public static AjaxResult error(){
        return error(500,"操作失败！",null);
    }

    public static AjaxResult error(int code){
        return error(code,"操作失败！",null);
    }

    public static AjaxResult genTable(List<?> list){
        AjaxResult ajax = new AjaxResult();
        ajax.put("code", 200);
        ajax.put("data", list);
        ajax.put("msg","操作成功");
        ajax.put("total", new PageInfo<>(list).getTotal());
        return ajax;
    }
}
