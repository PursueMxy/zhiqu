package com.icarexm.zhiquwang.bean;

public class OverTimeBean {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"hours":0,"over_price":0,"price":1110,"base_pay":"1000.00"}
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
         * price : 1110
         * base_pay : 1000.00
         */

        private double hours;
        private double over_price;
        private double price;
        private String base_pay;

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

        public String getBase_pay() {
            return base_pay;
        }

        public void setBase_pay(String base_pay) {
            this.base_pay = base_pay;
        }
    }
}
