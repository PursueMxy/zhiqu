package com.icarexm.zhiquwang.bean;

public class WechatBean {

    /**
     * code : 10004
     * msg : 请前往绑定手机号码
     * data : {"openid":"oiQaLuFXdZEHawYYyp6XPDCE7BYI"}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * openid : oiQaLuFXdZEHawYYyp6XPDCE7BYI
         */

        private String openid;

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }
    }
}
