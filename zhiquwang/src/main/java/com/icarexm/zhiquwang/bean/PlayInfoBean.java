package com.icarexm.zhiquwang.bean;

public class PlayInfoBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"start_time":"","start_status":1,"end_time":"","end_status":1,"is_auth":2,"address":null}
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
         * start_time :
         * start_status : 1
         * end_time :
         * end_status : 1
         * is_auth : 2
         */

        private String start_time;
        private int start_status;
        private String end_time;
        private int end_status;
        private int is_auth;
        private Object address;

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public int getStart_status() {
            return start_status;
        }

        public void setStart_status(int start_status) {
            this.start_status = start_status;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public int getEnd_status() {
            return end_status;
        }

        public void setEnd_status(int end_status) {
            this.end_status = end_status;
        }

        public int getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(int is_auth) {
            this.is_auth = is_auth;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }
    }
}
