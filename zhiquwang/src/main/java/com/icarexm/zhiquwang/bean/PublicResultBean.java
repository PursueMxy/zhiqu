package com.icarexm.zhiquwang.bean;

public class PublicResultBean {

    /**
     * code : 1
     * msg : 登录成功
     * data : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ6cWFwcCIsImF1ZCI6InpxYXBwIiwiaWF0IjoxNTc0MDQzMzEwLCJ1c2VyX2lkIjozfQ.4byx3UQSUIXds33eQfayXAu62h0PVglAqGyEC7RCqlc
     */

    private int code;
    private String msg;
    private String data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
