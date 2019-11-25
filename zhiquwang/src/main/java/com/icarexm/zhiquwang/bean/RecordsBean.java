package com.icarexm.zhiquwang.bean;

import java.util.List;

public class RecordsBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total_price":0,"total_time":0,"total_day":0,"total_info":[{"classes_id":2,"classes_name":"白班","price":0,"hours":0,"info":[{"festival_id":3,"festival_name":"中秋","price":0,"hours":0},{"festival_id":4,"festival_name":"工作日","price":0,"hours":0},{"festival_id":2,"festival_name":"国庆","price":0,"hours":0}]},{"classes_id":3,"classes_name":"夜班","price":0,"hours":0,"info":[{"festival_id":3,"festival_name":"中秋","price":0,"hours":0},{"festival_id":4,"festival_name":"工作日","price":0,"hours":0},{"festival_id":2,"festival_name":"国庆","price":0,"hours":0}]}]}
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
         * total_time : 0
         * total_day : 0
         * total_info : [{"classes_id":2,"classes_name":"白班","price":0,"hours":0,"info":[{"festival_id":3,"festival_name":"中秋","price":0,"hours":0},{"festival_id":4,"festival_name":"工作日","price":0,"hours":0},{"festival_id":2,"festival_name":"国庆","price":0,"hours":0}]},{"classes_id":3,"classes_name":"夜班","price":0,"hours":0,"info":[{"festival_id":3,"festival_name":"中秋","price":0,"hours":0},{"festival_id":4,"festival_name":"工作日","price":0,"hours":0},{"festival_id":2,"festival_name":"国庆","price":0,"hours":0}]}]
         */

        private int total_price;
        private int total_time;
        private int total_day;
        private List<TotalInfoBean> total_info;

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public int getTotal_time() {
            return total_time;
        }

        public void setTotal_time(int total_time) {
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
             * classes_id : 2
             * classes_name : 白班
             * price : 0
             * hours : 0
             * info : [{"festival_id":3,"festival_name":"中秋","price":0,"hours":0},{"festival_id":4,"festival_name":"工作日","price":0,"hours":0},{"festival_id":2,"festival_name":"国庆","price":0,"hours":0}]
             */

            private int classes_id;
            private String classes_name;
            private int price;
            private int hours;
            private List<InfoBean> info;

            public int getClasses_id() {
                return classes_id;
            }

            public void setClasses_id(int classes_id) {
                this.classes_id = classes_id;
            }

            public String getClasses_name() {
                return classes_name;
            }

            public void setClasses_name(String classes_name) {
                this.classes_name = classes_name;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public int getHours() {
                return hours;
            }

            public void setHours(int hours) {
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
                 * festival_id : 3
                 * festival_name : 中秋
                 * price : 0
                 * hours : 0
                 */

                private int festival_id;
                private String festival_name;
                private int price;
                private int hours;

                public int getFestival_id() {
                    return festival_id;
                }

                public void setFestival_id(int festival_id) {
                    this.festival_id = festival_id;
                }

                public String getFestival_name() {
                    return festival_name;
                }

                public void setFestival_name(String festival_name) {
                    this.festival_name = festival_name;
                }

                public int getPrice() {
                    return price;
                }

                public void setPrice(int price) {
                    this.price = price;
                }

                public int getHours() {
                    return hours;
                }

                public void setHours(int hours) {
                    this.hours = hours;
                }
            }
        }
    }
}
