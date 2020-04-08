package com.icarexm.zhiquwang.bean;

public class PosterBean {

    /**
     * code : 1
     * msg : 获取成功~
     * data : {"poster_url":"/invite/invite-X0q4nA-3.png"}
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
         * poster_url : /invite/invite-X0q4nA-3.png
         */

        private String poster_url;

        public String getPoster_url() {
            return poster_url;
        }

        public void setPoster_url(String poster_url) {
            this.poster_url = poster_url;
        }
    }
}
