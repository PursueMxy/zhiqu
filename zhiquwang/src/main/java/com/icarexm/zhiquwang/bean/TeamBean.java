package com.icarexm.zhiquwang.bean;

public class TeamBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"avatar":"http://192.168.1.22:8080/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","total_commission":"0.00","user_name":"黑铁5","login_time_text":""}
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
         * avatar : http://192.168.1.22:8080/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg
         * total_commission : 0.00
         * user_name : 黑铁5
         * login_time_text :
         */

        private String avatar;
        private String total_commission;
        private String user_name;
        private String login_time_text;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getTotal_commission() {
            return total_commission;
        }

        public void setTotal_commission(String total_commission) {
            this.total_commission = total_commission;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getLogin_time_text() {
            return login_time_text;
        }

        public void setLogin_time_text(String login_time_text) {
            this.login_time_text = login_time_text;
        }
    }
}
