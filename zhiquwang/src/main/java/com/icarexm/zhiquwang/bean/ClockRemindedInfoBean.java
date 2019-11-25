package com.icarexm.zhiquwang.bean;

public class ClockRemindedInfoBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"user_id":3,"start_time":"04:00","end_time":"12:00","repetition":"2,3,4","create_time":"2019-11-25 16:17:42"}
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
         * user_id : 3
         * start_time : 04:00
         * end_time : 12:00
         * repetition : 2,3,4
         * create_time : 2019-11-25 16:17:42
         */

        private int user_id;
        private String start_time;
        private String end_time;
        private String repetition;
        private String create_time;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public String getRepetition() {
            return repetition;
        }

        public void setRepetition(String repetition) {
            this.repetition = repetition;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
