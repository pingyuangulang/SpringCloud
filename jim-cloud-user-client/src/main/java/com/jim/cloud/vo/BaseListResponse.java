package com.jim.cloud.vo;

import lombok.Data;

import java.util.List;

/**
 * 接口统一返回类型（数据对象集合）
 *
 * @author jim
 * @date 2019/6/15 23:54
 */
@Data
public class BaseListResponse<T> {

    private int code;

    private String msg;

    private List<T> data;

    public BaseListResponse() {
        this.code = 200;
        this.msg = "success";
        this.data = null;
    }

    public BaseListResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public BaseListResponse(int code, String msg, List<T> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<T> getData() {
        return data;
    }

    public BaseListResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public BaseListResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseListResponse setData(List<T> data) {
        this.data = data;
        return this;
    }
}
