package com.icarexm.zhiquwang.bean;

public class BaseInforBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"mobile":"15306987579","avatar":"/assets/img/avatar.png","is_auth":"未认证","user_name":"大佬","login_time_text":""}
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
         * mobile : 15306987579
         * avatar : /assets/img/avatar.png
         * is_auth : 未认证
         * user_name : 大佬
         * login_time_text :
         */

        private String mobile;
        private String avatar;
        private String is_auth;
        private String user_name;
        private String login_time_text;

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

        public String getIs_auth() {
            return is_auth;
        }

        public void setIs_auth(String is_auth) {
            this.is_auth = is_auth;
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
