package com.zab.bootdecoupling.common;

public class Msg<T> {
    /**
     * 0表示正常，1表示异常
     */
    private String code;
    /**
     * 面向用户的消息
     */
    private String message;
    /**
     * 面向开发者的详细信息
     */
    private String detail;
    /**
     * 返回给前端的数据
     */
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Msg(String code, String message, String detail, T data) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.data = data;
    }

    public static Msg success(String message, String detail) {
        return new Msg("0", message, detail, null);
    }

    public static Msg fail(String message, String detail) {
        return new Msg("1", message, detail, null);
    }
}
