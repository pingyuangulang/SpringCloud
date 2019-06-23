package com.jim.cloud.response.vo;


/**
 * 接口统一返回类型（单个数据对象）
 *
 * @author jim
 * @date 2019/6/15 23:51
 */
public class BaseSingleResponse<T> {

    private int code;

    private String msg;

    private T data;

    public BaseSingleResponse() {
        this.code = 200;
        this.msg = "success";
        this.data = null;
    }

    public BaseSingleResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public BaseSingleResponse(int code, String msg, T data) {
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

    public T getData() {
        return data;
    }

    public BaseSingleResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public BaseSingleResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public BaseSingleResponse setData(T data) {
        this.data = data;
        return this;
    }
}
