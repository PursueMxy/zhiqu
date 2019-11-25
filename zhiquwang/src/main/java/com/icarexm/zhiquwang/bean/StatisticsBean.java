package com.icarexm.zhiquwang.bean;

import java.util.List;

public class StatisticsBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total_price":0,"total_time":1.5,"total_day":1,"total_info":[{"classes_name":null,"price":"0.00","hours":"1.50","info":[{"festival_name":"","price":"0.00","hours":"1.50"}]}]}
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
         * total_price : 0
         * total_time : 1.5
         * total_day : 1
         * total_info : [{"classes_name":null,"price":"0.00","hours":"1.50","info":[{"festival_name":"","price":"0.00","hours":"1.50"}]}]
         */

        private int total_price;
        private double total_time;
        private int total_day;
        private List<TotalInfoBean> total_info;

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public double getTotal_time() {
            return total_time;
        }

        public void setTotal_time(double total_time) {
            this.total_time = total_time;
        }

        public int getTotal_day() {
            return total_day;
        }

        public void setTotal_day(int total_day) {
            this.total_day = total_day;
        }

        public List<TotalInfoBean> getTotal_info() {
            return total_info;
        }

        public void setTotal_info(List<TotalInfoBean> total_info) {
            this.total_info = total_info;
        }

        public static class TotalInfoBean {
            /**
             * classes_name : null
             * price : 0.00
             * hours : 1.50
             * info : [{"festival_name":"","price":"0.00","hours":"1.50"}]
             */

            private Object classes_name;
            private String price;
            private String hours;
            private List<InfoBean> info;

            public Object getClasses_name() {
                return classes_name;
            }

            public void setClasses_name(Object classes_name) {
                this.classes_name = classes_name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public List<InfoBean> getInfo() {
                return info;
            }

            public void setInfo(List<InfoBean> info) {
                this.info = info;
            }

            public static class InfoBean {
                /**
                 * festival_name :
                 * price : 0.00
                 * hours : 1.50
                 */

                private String festival_name;
                private String price;
                private String hours;

                public String getFestival_name() {
                    return festival_name;
                }

                public void setFestival_name(String festival_name) {
                    this.festival_name = festival_name;
                }

                public String getPrice() {
                    return price;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getHours() {
                    return hours;
                }

                public void setHours(String hours) {
                    this.hours = hours;
                }
            }
        }
    }
}
