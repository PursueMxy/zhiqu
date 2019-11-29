package com.icarexm.zhiquwang.bean;

public class LoginBean {

    /**
     * code : 1
     * msg : 登录成功
     * data : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ6cWFwcCIsImF1ZCI6InpxYXBwIiwiaWF0IjoxNTc0OTk0NDk5LCJ1c2VyX2lkIjozfQ._U4VFbJOKEShbfsWINcNony5xYq9r1_InGDYSLMsRZ8
     * other : 3
     */

    private int code;
    private String msg;
    private String data;
    private int other;

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

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}
