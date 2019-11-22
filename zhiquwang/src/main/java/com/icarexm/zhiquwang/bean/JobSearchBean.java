package com.icarexm.zhiquwang.bean;

import java.util.List;

public class JobSearchBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total":3,"per_page":"20","current_page":1,"last_page":1,"data":[{"audit":1,"job_id":1,"job_name":"第一个发布职位","label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"返现//*","salary_hour":"25.00","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","salary":"500.00-500.00","age":"18-35"},{"audit":1,"job_id":3,"job_name":"第三个","label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"5","salary_hour":"25.00","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","salary":"500.00-500.00","age":"18-35"},{"audit":1,"job_id":2,"job_name":"第二个","label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"5","salary_hour":"25.00","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","salary":"500.00-500.00","age":"18-35"}]}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"audit":1,"job_id":1,"job_name":"第一个发布职位","label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"返现//*","salary_hour":"25.00","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","salary":"500.00-500.00","age":"18-35"},{"audit":1,"job_id":3,"job_name":"第三个","label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"5","salary_hour":"25.00","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","salary":"500.00-500.00","age":"18-35"},{"audit":1,"job_id":2,"job_name":"第二个","label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"5","salary_hour":"25.00","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","salary":"500.00-500.00","age":"18-35"}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * audit : 1
             * job_id : 1
             * job_name : 第一个发布职位
             * label_arr : [{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}]
             * label_price : 返现//*
             * salary_hour : 25.00
             * longitude : 118.110220
             * latitude : 24.490474
             * address : 福建省厦门市思明区筼筜街道仙岳路163古石雕大观园
             * salary : 500.00-500.00
             * age : 18-35
             */

            private int audit;
            private int job_id;
            private String job_name;
            private String label_price;
            private String salary_hour;
            private String longitude;
            private String latitude;
            private String address;
            private String salary;
            private String age;
            private List<LabelArrBean> label_arr;

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public int getJob_id() {
                return job_id;
            }

            public void setJob_id(int job_id) {
                this.job_id = job_id;
            }

            public String getJob_name() {
                return job_name;
            }

            public void setJob_name(String job_name) {
                this.job_name = job_name;
            }

            public String getLabel_price() {
                return label_price;
            }

            public void setLabel_price(String label_price) {
                this.label_price = label_price;
            }

            public String getSalary_hour() {
                return salary_hour;
            }

            public void setSalary_hour(String salary_hour) {
                this.salary_hour = salary_hour;
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

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getSalary() {
                return salary;
            }

            public void setSalary(String salary) {
                this.salary = salary;
            }

            public String getAge() {
                return age;
            }

            public void setAge(String age) {
                this.age = age;
            }

            public List<LabelArrBean> getLabel_arr() {
                return label_arr;
            }

            public void setLabel_arr(List<LabelArrBean> label_arr) {
                this.label_arr = label_arr;
            }

            public static class LabelArrBean {
                /**
                 * label_name : 猜不透的结局
                 */

                private String label_name;

                public String getLabel_name() {
                    return label_name;
                }

                public void setLabel_name(String label_name) {
                    this.label_name = label_name;
                }
            }
        }
    }
}
