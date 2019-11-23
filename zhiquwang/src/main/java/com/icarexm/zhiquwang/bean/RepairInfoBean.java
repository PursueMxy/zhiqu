package com.icarexm.zhiquwang.bean;

import java.util.List;

public class RepairInfoBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"repair_num":4,"month":[{"witch_day":1,"start_time":"","end_time":"","is_play":1},{"witch_day":2,"start_time":"","end_time":"","is_play":3},{"witch_day":3,"start_time":"","end_time":"","is_play":3},{"witch_day":4,"start_time":"","end_time":"","is_play":1},{"witch_day":5,"start_time":"","end_time":"","is_play":1},{"witch_day":6,"start_time":"","end_time":"","is_play":1},{"witch_day":7,"start_time":"","end_time":"","is_play":1},{"witch_day":8,"start_time":"","end_time":"","is_play":1},{"witch_day":9,"start_time":"","end_time":"","is_play":3},{"witch_day":10,"start_time":"","end_time":"","is_play":3},{"witch_day":11,"start_time":"","end_time":"","is_play":1},{"witch_day":12,"start_time":"","end_time":"","is_play":1},{"witch_day":13,"start_time":"","end_time":"","is_play":1},{"witch_day":14,"start_time":"","end_time":"","is_play":1},{"witch_day":15,"start_time":"","end_time":"","is_play":1},{"witch_day":16,"start_time":"","end_time":"","is_play":3},{"witch_day":17,"start_time":"","end_time":"","is_play":3},{"witch_day":18,"start_time":"","end_time":"","is_play":1},{"witch_day":19,"start_time":"","end_time":"","is_play":1},{"witch_day":20,"start_time":"","end_time":"","is_play":1},{"witch_day":21,"start_time":"10:27","end_time":"10:27","is_play":2},{"witch_day":22,"start_time":"18:15","end_time":"10:45","is_play":2},{"witch_day":23,"start_time":"10:19","end_time":"10:45","is_play":2}]}
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
         * repair_num : 4
         * month : [{"witch_day":1,"start_time":"","end_time":"","is_play":1},{"witch_day":2,"start_time":"","end_time":"","is_play":3},{"witch_day":3,"start_time":"","end_time":"","is_play":3},{"witch_day":4,"start_time":"","end_time":"","is_play":1},{"witch_day":5,"start_time":"","end_time":"","is_play":1},{"witch_day":6,"start_time":"","end_time":"","is_play":1},{"witch_day":7,"start_time":"","end_time":"","is_play":1},{"witch_day":8,"start_time":"","end_time":"","is_play":1},{"witch_day":9,"start_time":"","end_time":"","is_play":3},{"witch_day":10,"start_time":"","end_time":"","is_play":3},{"witch_day":11,"start_time":"","end_time":"","is_play":1},{"witch_day":12,"start_time":"","end_time":"","is_play":1},{"witch_day":13,"start_time":"","end_time":"","is_play":1},{"witch_day":14,"start_time":"","end_time":"","is_play":1},{"witch_day":15,"start_time":"","end_time":"","is_play":1},{"witch_day":16,"start_time":"","end_time":"","is_play":3},{"witch_day":17,"start_time":"","end_time":"","is_play":3},{"witch_day":18,"start_time":"","end_time":"","is_play":1},{"witch_day":19,"start_time":"","end_time":"","is_play":1},{"witch_day":20,"start_time":"","end_time":"","is_play":1},{"witch_day":21,"start_time":"10:27","end_time":"10:27","is_play":2},{"witch_day":22,"start_time":"18:15","end_time":"10:45","is_play":2},{"witch_day":23,"start_time":"10:19","end_time":"10:45","is_play":2}]
         */

        private int repair_num;
        private List<MonthBean> month;

        public int getRepair_num() {
            return repair_num;
        }

        public void setRepair_num(int repair_num) {
            this.repair_num = repair_num;
        }

        public List<MonthBean> getMonth() {
            return month;
        }

        public void setMonth(List<MonthBean> month) {
            this.month = month;
        }

        public DataBean(List<MonthBean> month) {
            this.month = month;
        }

        public static class MonthBean {
            /**
             * witch_day : 1
             * start_time :
             * end_time :
             * is_play : 1
             */

            private int witch_day;
            private String start_time;
            private String end_time;
            private int is_play;

            public int getWitch_day() {
                return witch_day;
            }

            public void setWitch_day(int witch_day) {
                this.witch_day = witch_day;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public int getIs_play() {
                return is_play;
            }

            public void setIs_play(int is_play) {
                this.is_play = is_play;
            }

            public MonthBean(int witch_day, String start_time, String end_time, int is_play) {
                this.witch_day = witch_day;
                this.start_time = start_time;
                this.end_time = end_time;
                this.is_play = is_play;
            }
        }
    }
}
