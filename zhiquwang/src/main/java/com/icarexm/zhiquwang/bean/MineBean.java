package com.icarexm.zhiquwang.bean;

public class MineBean {


    /**
     * code : 1
     * msg : success
     * data : {"user_id":3,"username":"黑铁5","real_name":"黑铁5","mobile":"15306987579","avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","city":"福建厦门市","unread_num":0,"login_time_text":""}
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
         * username : 黑铁5
         * real_name : 黑铁5
         * mobile : 15306987579
         * avatar : /uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg
         * city : 福建厦门市
         * unread_num : 0
         * login_time_text :
         */

        private int user_id;
        private String username;
        private String real_name;
        private String mobile;
        private String avatar;
        private String city;
        private int unread_num;
        private String login_time_text;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

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

        public int getUnread_num() {
            return unread_num;
        }

        public void setUnread_num(int unread_num) {
            this.unread_num = unread_num;
        }

        public String getLogin_time_text() {
            return login_time_text;
        }

        public void setLogin_time_text(String login_time_text) {
            this.login_time_text = login_time_text;
        }
    }
}
