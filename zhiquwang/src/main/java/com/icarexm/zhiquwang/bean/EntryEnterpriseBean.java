package com.icarexm.zhiquwang.bean;

import java.util.List;

public class EntryEnterpriseBean {

    /**
     * code : 1
     * msg : 获取成功~
     * data : {"job_zone":4,"job_id":26,"job_name":"翔安乾照光电20元/时","label_arr":[{"label_name":"电子行业"}],"label_price":"返50元/月","salary_start":"5300.00","salary_end":"6500.00","salary_hour":"5300.00","salary_structure":"","age_start":18,"age_end":35,"longitude":"118.241806","latitude":"24.650036","address":"福建省厦门市翔安区马巷镇翔天路259-269号火炬翔安产业区","salary":"5300.00-6500.00","age":"18-35","job_time":"2019-12-29","job_days":1,"play_days":0}
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
         * job_zone : 4
         * job_id : 26
         * job_name : 翔安乾照光电20元/时
         * label_arr : [{"label_name":"电子行业"}]
         * label_price : 返50元/月
         * salary_start : 5300.00
         * salary_end : 6500.00
         * salary_hour : 5300.00
         * salary_structure :
         * age_start : 18
         * age_end : 35
         * longitude : 118.241806
         * latitude : 24.650036
         * address : 福建省厦门市翔安区马巷镇翔天路259-269号火炬翔安产业区
         * salary : 5300.00-6500.00
         * age : 18-35
         * job_time : 2019-12-29
         * job_days : 1
         * play_days : 0
         */

        private int job_zone;
        private int job_id;
        private String job_name;
        private String label_price;
        private String salary_start;
        private String salary_end;
        private String salary_hour;
        private String salary_structure;
        private int age_start;
        private int age_end;
        private String longitude;
        private String latitude;
        private String address;
        private String salary;
        private String age;
        private String job_time;
        private int job_days;
        private int play_days;
        private List<LabelArrBean> label_arr;

        public int getJob_zone() {
            return job_zone;
        }

        public void setJob_zone(int job_zone) {
            this.job_zone = job_zone;
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

        public String getSalary_start() {
            return salary_start;
        }

        public void setSalary_start(String salary_start) {
            this.salary_start = salary_start;
        }

        public String getSalary_end() {
            return salary_end;
        }

        public void setSalary_end(String salary_end) {
            this.salary_end = salary_end;
        }

        public String getSalary_hour() {
            return salary_hour;
        }

        public void setSalary_hour(String salary_hour) {
            this.salary_hour = salary_hour;
        }

        public String getSalary_structure() {
            return salary_structure;
        }

        public void setSalary_structure(String salary_structure) {
            this.salary_structure = salary_structure;
        }

        public int getAge_start() {
            return age_start;
        }

        public void setAge_start(int age_start) {
            this.age_start = age_start;
        }

        public int getAge_end() {
            return age_end;
        }

        public void setAge_end(int age_end) {
            this.age_end = age_end;
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

        public String getJob_time() {
            return job_time;
        }

        public void setJob_time(String job_time) {
            this.job_time = job_time;
        }

        public int getJob_days() {
            return job_days;
        }

        public void setJob_days(int job_days) {
            this.job_days = job_days;
        }

        public int getPlay_days() {
            return play_days;
        }

        public void setPlay_days(int play_days) {
            this.play_days = play_days;
        }

        public List<LabelArrBean> getLabel_arr() {
            return label_arr;
        }

        public void setLabel_arr(List<LabelArrBean> label_arr) {
            this.label_arr = label_arr;
        }

        public static class LabelArrBean {
            /**
             * label_name : 电子行业
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
