package com.icarexm.zhiquwang.bean;

import java.util.List;

public class BasePayBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"base_pay":0,"subsidy":[{"subsidy_id":2,"subsidy_name":"餐费补贴","price":0},{"subsidy_id":3,"subsidy_name":"交通补贴","price":0}]}
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
         * base_pay : 0
         * subsidy : [{"subsidy_id":2,"subsidy_name":"餐费补贴","price":0},{"subsidy_id":3,"subsidy_name":"交通补贴","price":0}]
         */

        private int base_pay;
        private List<SubsidyBean> subsidy;

        public int getBase_pay() {
            return base_pay;
        }

        public void setBase_pay(int base_pay) {
            this.base_pay = base_pay;
        }

        public List<SubsidyBean> getSubsidy() {
            return subsidy;
        }

        public void setSubsidy(List<SubsidyBean> subsidy) {
            this.subsidy = subsidy;
        }

        public static class SubsidyBean {
            /**
             * subsidy_id : 2
             * subsidy_name : 餐费补贴
             * price : 0
             */

            private int subsidy_id;
            private String subsidy_name;
            private int price;

            public int getSubsidy_id() {
                return subsidy_id;
            }

            public void setSubsidy_id(int subsidy_id) {
                this.subsidy_id = subsidy_id;
            }

            public String getSubsidy_name() {
                return subsidy_name;
            }

            public void setSubsidy_name(String subsidy_name) {
                this.subsidy_name = subsidy_name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }
        }
    }
}
