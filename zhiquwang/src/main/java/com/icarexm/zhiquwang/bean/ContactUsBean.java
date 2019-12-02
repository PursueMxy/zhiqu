package com.icarexm.zhiquwang.bean;

public class ContactUsBean {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"contact_us":" 1654684626 6846815 ","contact_img":"/uploads/20191111/baa38e2a5c05a90d0e752dfb0da2c654.png","mobile":"13333333333","address":"福建省厦门市思明区梧村街道厦门国贸大厦","longitude":"118.119068","latitude":"24.475965"}
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
         * contact_us :  1654684626 6846815
         * contact_img : /uploads/20191111/baa38e2a5c05a90d0e752dfb0da2c654.png
         * mobile : 13333333333
         * address : 福建省厦门市思明区梧村街道厦门国贸大厦
         * longitude : 118.119068
         * latitude : 24.475965
         */

        private String contact_us;
        private String contact_img;
        private String mobile;
        private String address;
        private String longitude;
        private String latitude;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }
    }
}
