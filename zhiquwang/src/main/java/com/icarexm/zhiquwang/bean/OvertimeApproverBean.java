package com.icarexm.zhiquwang.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OvertimeApproverBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"month":[{"day":1,"hours":"","classes_id":"","festival_id":""},{"day":2,"hours":"","classes_id":"","festival_id":""},{"day":3,"hours":"","classes_id":"","festival_id":""},{"day":4,"hours":"","classes_id":"","festival_id":""},{"day":5,"hours":"","classes_id":"","festival_id":""},{"day":6,"hours":"","classes_id":"","festival_id":""},{"day":7,"hours":"","classes_id":"","festival_id":""},{"day":8,"hours":"","classes_id":"","festival_id":""},{"day":9,"hours":"","classes_id":"","festival_id":""},{"day":10,"hours":"","classes_id":"","festival_id":""},{"day":11,"hours":"","classes_id":"","festival_id":""},{"day":12,"hours":"","classes_id":"","festival_id":""},{"day":13,"hours":"","classes_id":"","festival_id":""},{"day":14,"hours":"","classes_id":"","festival_id":""},{"day":15,"hours":"","classes_id":"","festival_id":""},{"day":16,"hours":"","classes_id":"","festival_id":""},{"day":17,"hours":"","classes_id":"","festival_id":""},{"day":18,"hours":"","classes_id":"","festival_id":""},{"day":19,"hours":"","classes_id":"","festival_id":""},{"day":20,"hours":"","classes_id":"","festival_id":""},{"day":21,"hours":"","classes_id":"","festival_id":""},{"day":22,"hours":"","classes_id":"","festival_id":""},{"day":23,"hours":"","classes_id":"","festival_id":""},{"day":24,"hours":"","classes_id":"","festival_id":""}],"classes":{"2":"白班","3":"夜班"},"festival":{"2":"国庆","3":"中秋","4":"工作日"}}
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
         * month : [{"day":1,"hours":"","classes_id":"","festival_id":""},{"day":2,"hours":"","classes_id":"","festival_id":""},{"day":3,"hours":"","classes_id":"","festival_id":""},{"day":4,"hours":"","classes_id":"","festival_id":""},{"day":5,"hours":"","classes_id":"","festival_id":""},{"day":6,"hours":"","classes_id":"","festival_id":""},{"day":7,"hours":"","classes_id":"","festival_id":""},{"day":8,"hours":"","classes_id":"","festival_id":""},{"day":9,"hours":"","classes_id":"","festival_id":""},{"day":10,"hours":"","classes_id":"","festival_id":""},{"day":11,"hours":"","classes_id":"","festival_id":""},{"day":12,"hours":"","classes_id":"","festival_id":""},{"day":13,"hours":"","classes_id":"","festival_id":""},{"day":14,"hours":"","classes_id":"","festival_id":""},{"day":15,"hours":"","classes_id":"","festival_id":""},{"day":16,"hours":"","classes_id":"","festival_id":""},{"day":17,"hours":"","classes_id":"","festival_id":""},{"day":18,"hours":"","classes_id":"","festival_id":""},{"day":19,"hours":"","classes_id":"","festival_id":""},{"day":20,"hours":"","classes_id":"","festival_id":""},{"day":21,"hours":"","classes_id":"","festival_id":""},{"day":22,"hours":"","classes_id":"","festival_id":""},{"day":23,"hours":"","classes_id":"","festival_id":""},{"day":24,"hours":"","classes_id":"","festival_id":""}]
         * classes : {"2":"白班","3":"夜班"}
         * festival : {"2":"国庆","3":"中秋","4":"工作日"}
         */

        private ClassesBean classes;
        private FestivalBean festival;
        private List<MonthBean> month;

        public ClassesBean getClasses() {
            return classes;
        }

        public void setClasses(ClassesBean classes) {
            this.classes = classes;
        }

        public FestivalBean getFestival() {
            return festival;
        }

        public void setFestival(FestivalBean festival) {
            this.festival = festival;
        }

        public List<MonthBean> getMonth() {
            return month;
        }

        public void setMonth(List<MonthBean> month) {
            this.month = month;
        }

        public static class ClassesBean {
            /**
             * 2 : 白班
             * 3 : 夜班
             */

            @SerializedName("2")
            private String _$2;
            @SerializedName("3")
            private String _$3;

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }
        }

        public static class FestivalBean {
            /**
             * 2 : 国庆
             * 3 : 中秋
             * 4 : 工作日
             */

            @SerializedName("2")
            private String _$2;
            @SerializedName("3")
            private String _$3;
            @SerializedName("4")
            private String _$4;

            public String get_$2() {
                return _$2;
            }

            public void set_$2(String _$2) {
                this._$2 = _$2;
            }

            public String get_$3() {
                return _$3;
            }

            public void set_$3(String _$3) {
                this._$3 = _$3;
            }

            public String get_$4() {
                return _$4;
            }

            public void set_$4(String _$4) {
                this._$4 = _$4;
            }
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
    }
}
