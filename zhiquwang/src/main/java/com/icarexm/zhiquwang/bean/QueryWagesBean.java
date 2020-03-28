package com.icarexm.zhiquwang.bean;

public class QueryWagesBean {


    /**
     * code : 1
     * msg : success
     * data : {"id":14,"name":"丁锦涓","card":"350629199901010011","num":"007","company":"在乎科技","month":"2020-03","month_price":"9999.00","base_price":"6666.00","hour_price":"88.00","hour":1,"extra_price":"77.00","duty_day":30,"subsidy":"1234.00","deduct_price":"0.00","tax_before":"18888.00","actual_price":"16666.00","create_time":"2020-03-21 18:53:40"}
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
         * id : 14
         * name : 丁锦涓
         * card : 350629199901010011
         * num : 007
         * company : 在乎科技
         * month : 2020-03
         * month_price : 9999.00
         * base_price : 6666.00
         * hour_price : 88.00
         * hour : 1
         * extra_price : 77.00
         * duty_day : 30
         * subsidy : 1234.00
         * deduct_price : 0.00
         * tax_before : 18888.00
         * actual_price : 16666.00
         * create_time : 2020-03-21 18:53:40
         */

        private int id;
        private String name;
        private String card;
        private String num;
        private String company;
        private String month;
        private String month_price;
        private String base_price;
        private String hour_price;
        private int hour;
        private String extra_price;
        private int duty_day;
        private String subsidy;
        private String deduct_price;
        private String tax_before;
        private String actual_price;
        private String create_time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCard() {
            return card;
        }

        public void setCard(String card) {
            this.card = card;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getMonth_price() {
            return month_price;
        }

        public void setMonth_price(String month_price) {
            this.month_price = month_price;
        }

        public String getBase_price() {
            return base_price;
        }

        public void setBase_price(String base_price) {
            this.base_price = base_price;
        }

        public String getHour_price() {
            return hour_price;
        }

        public void setHour_price(String hour_price) {
            this.hour_price = hour_price;
        }

        public int getHour() {
            return hour;
        }

        public void setHour(int hour) {
            this.hour = hour;
        }

        public String getExtra_price() {
            return extra_price;
        }

        public void setExtra_price(String extra_price) {
            this.extra_price = extra_price;
        }

        public int getDuty_day() {
            return duty_day;
        }

        public void setDuty_day(int duty_day) {
            this.duty_day = duty_day;
        }

        public String getSubsidy() {
            return subsidy;
        }

        public void setSubsidy(String subsidy) {
            this.subsidy = subsidy;
        }

        public String getDeduct_price() {
            return deduct_price;
        }

        public void setDeduct_price(String deduct_price) {
            this.deduct_price = deduct_price;
        }

        public String getTax_before() {
            return tax_before;
        }

        public void setTax_before(String tax_before) {
            this.tax_before = tax_before;
        }

        public String getActual_price() {
            return actual_price;
        }

        public void setActual_price(String actual_price) {
            this.actual_price = actual_price;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
