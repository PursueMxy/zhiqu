package com.icarexm.zhiquwang.bean;

public class UploadImgBean {

    /**
     * code : 1
     * msg : 上传成功
     * data : {"url":"/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg"}
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
         * url : /uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg
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
