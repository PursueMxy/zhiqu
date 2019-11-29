package com.icarexm.zhiquwang.bean;

public class ContactUsBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"contact_us":"<ol><li>1654684626<\/li><li>6846815<\/li><\/ol>","contact_img":"/uploads/20191111/baa38e2a5c05a90d0e752dfb0da2c654.png","mobile":"13333333333"}
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
         * contact_us : <ol><li>1654684626</li><li>6846815</li></ol>
         * contact_img : /uploads/20191111/baa38e2a5c05a90d0e752dfb0da2c654.png
         * mobile : 13333333333
         */

        private String contact_us;
        private String contact_img;
        private String mobile;

        public String getContact_us() {
            return contact_us;
        }

        public void setContact_us(String contact_us) {
            this.contact_us = contact_us;
        }

        public String getContact_img() {
            return contact_img;
        }

        public void setContact_img(String contact_img) {
            this.contact_img = contact_img;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
