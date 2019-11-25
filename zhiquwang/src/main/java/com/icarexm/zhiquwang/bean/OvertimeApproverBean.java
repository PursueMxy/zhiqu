package com.icarexm.zhiquwang.bean;

import java.util.List;

public class OvertimeApproverBean {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"month":[{"day":1,"hours":"","classes_id":"","festival_id":""},{"day":2,"hours":"","classes_id":"","festival_id":""},{"day":3,"hours":"","classes_id":"","festival_id":""},{"day":4,"hours":"","classes_id":"","festival_id":""},{"day":5,"hours":"","classes_id":"","festival_id":""},{"day":6,"hours":"","classes_id":"","festival_id":""},{"day":7,"hours":"","classes_id":"","festival_id":""},{"day":8,"hours":"","classes_id":"","festival_id":""},{"day":9,"hours":"","classes_id":"","festival_id":""},{"day":10,"hours":"","classes_id":"","festival_id":""},{"day":11,"hours":"","classes_id":"","festival_id":""},{"day":12,"hours":"","classes_id":"","festival_id":""},{"day":13,"hours":"","classes_id":"","festival_id":""},{"day":14,"hours":"","classes_id":"","festival_id":""},{"day":15,"hours":"","classes_id":"","festival_id":""},{"day":16,"hours":"","classes_id":"","festival_id":""},{"day":17,"hours":"","classes_id":"","festival_id":""},{"day":18,"hours":"","classes_id":"","festival_id":""},{"day":19,"hours":"","classes_id":"","festival_id":""},{"day":20,"hours":"","classes_id":"","festival_id":""},{"day":21,"hours":"","classes_id":"","festival_id":""},{"day":22,"hours":"","classes_id":"","festival_id":""},{"day":23,"hours":"","classes_id":"","festival_id":""},{"day":24,"hours":"","classes_id":"","festival_id":""},{"day":25,"hours":"","classes_id":"","festival_id":""}],"classes":[{"classes_name":"白班","classes_id":2},{"classes_name":"夜班","classes_id":3}],"festival":[{"festival_name":"国庆","festival_id":2},{"festival_name":"中秋","festival_id":3},{"festival_name":"工作日","festival_id":4}]}
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
        private List<MonthBean> month;
        private List<ClassesBean> classes;
        private List<FestivalBean> festival;

        public List<MonthBean> getMonth() {
            return month;
        }

        public void setMonth(List<MonthBean> month) {
            this.month = month;
        }

        public List<ClassesBean> getClasses() {
            return classes;
        }

        public void setClasses(List<ClassesBean> classes) {
            this.classes = classes;
        }

        public List<FestivalBean> getFestival() {
            return festival;
        }

        public void setFestival(List<FestivalBean> festival) {
            this.festival = festival;
        }

        public static class MonthBean {
            /**
             * day : 1
             * hours :
             * classes_id :
             * festival_id :
             */

            private int day;
            private String hours;
            private String classes_id;
            private String festival_id;

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getHours() {
                return hours;
            }

            public void setHours(String hours) {
                this.hours = hours;
            }

            public String getClasses_id() {
                return classes_id;
            }

            public void setClasses_id(String classes_id) {
                this.classes_id = classes_id;
            }

            public String getFestival_id() {
                return festival_id;
            }

            public void setFestival_id(String festival_id) {
                this.festival_id = festival_id;
            }
        }

        public static class ClassesBean {
            /**
             * classes_name : 白班
             * classes_id : 2
             */

            private String classes_name;
            private int classes_id;

            public String getClasses_name() {
                return classes_name;
            }

            public void setClasses_name(String classes_name) {
                this.classes_name = classes_name;
            }

            public int getClasses_id() {
                return classes_id;
            }

            public void setClasses_id(int classes_id) {
                this.classes_id = classes_id;
            }
        }

        public static class FestivalBean {
            /**
             * festival_name : 国庆
             * festival_id : 2
             */

            private String festival_name;
            private int festival_id;

            public String getFestival_name() {
                return festival_name;
            }

            public void setFestival_name(String festival_name) {
                this.festival_name = festival_name;
            }

            public int getFestival_id() {
                return festival_id;
            }

            public void setFestival_id(int festival_id) {
                this.festival_id = festival_id;
            }
        }
    }
}
