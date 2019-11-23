package com.icarexm.zhiquwang.bean;

public class OverTimeBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"hours":0,"over_price":0,"price":0}
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
         * hours : 0
         * over_price : 0
         * price : 0
         */

        private int hours;
        private int over_price;
        private int price;

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getOver_price() {
            return over_price;
        }

        public void setOver_price(int over_price) {
            this.over_price = over_price;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
