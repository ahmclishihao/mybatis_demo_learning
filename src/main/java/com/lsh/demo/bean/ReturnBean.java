package com.lsh.demo.bean;

public class ReturnBean {

    private static final String OK = "0";

    private static final String ERROR = "1";

    private String code;

    private String message;

    private Integer count;

    private Object data;

    public static ReturnBean ok(Object data){
        ReturnBean returnBean = new ReturnBean();
        returnBean.code = OK;
        returnBean.data = data;
        return returnBean;
    }

    public static ReturnBean error(Object data){
        ReturnBean returnBean = new ReturnBean();
        returnBean.code = ERROR;
        returnBean.data = data;
        return returnBean;
    }
    public static ReturnBean page(Object data,Integer total){
        ReturnBean returnBean = new ReturnBean();
        returnBean.code = OK;
        returnBean.data = data;
        returnBean.count = total;
        return returnBean;
    }

    public ReturnBean msg(String msg){
        this.message = msg;
        return this;
    }

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
