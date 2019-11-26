package com.icarexm.zhiquwang.bean;

public class MineBean {

    /**
     * code : 1
     * msg : success
     * data : {"username":"黑铁5","real_name":"黑铁5","mobile":"15306987579","avatar":"/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","city":"福建厦门市","login_time_text":""}
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
         * username : 黑铁5
         * real_name : 黑铁5
         * mobile : 15306987579
         * avatar : /uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg
         * city : 福建厦门市
         * login_time_text :
         */

        private String username;
        private String real_name;
        private String mobile;
        private String avatar;
        private String city;
        private String login_time_text;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getReal_name() {
            return real_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLogin_time_text() {
            return login_time_text;
        }

        public void setLogin_time_text(String login_time_text) {
            this.login_time_text = login_time_text;
        }
    }
}
