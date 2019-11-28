package com.icarexm.zhiquwang.bean;

public class InviteUrlBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"url":"https://zqw.kuaishanghd.com/zqv1/other/invite/salt/X0q4nA"}
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
         * url : https://zqw.kuaishanghd.com/zqv1/other/invite/salt/X0q4nA
         */

        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
