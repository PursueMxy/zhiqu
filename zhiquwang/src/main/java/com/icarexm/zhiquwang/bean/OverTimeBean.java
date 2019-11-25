package com.icarexm.zhiquwang.bean;

public class OverTimeBean {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"hours":1.5,"over_price":12.91,"price":1122.9099999999999}
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
         * hours : 1.5
         * over_price : 12.91
         * price : 1122.9099999999999
         */

        private double hours;
        private double over_price;
        private double price;

        public double getHours() {
            return hours;
        }

        public void setHours(double hours) {
            this.hours = hours;
        }

        public double getOver_price() {
            return over_price;
        }

        public void setOver_price(double over_price) {
            this.over_price = over_price;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
