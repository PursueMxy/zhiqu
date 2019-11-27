package com.icarexm.zhiquwang.bean;

import java.util.List;

public class MyTeanBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"total":3,"per_page":"20","current_page":1,"last_page":1,"data":[{"avatar":"/assets/img/avatar.png","login_time_text":"","user_name":""},{"avatar":"/uploads/20191127/9SwFPLj0CXMJ4rDbuc8yWQ2GUof5V6AZ.png","login_time_text":"","user_name":"MXY"},{"avatar":"/uploads/20191127/wYvlmBWNsyOHkMeSjF4uXgt9PTqaVzJ0.png","login_time_text":"","user_name":"NBFD-Alice"}]}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * total : 3
         * per_page : 20
         * current_page : 1
         * last_page : 1
         * data : [{"avatar":"/assets/img/avatar.png","login_time_text":"","user_name":""},{"avatar":"/uploads/20191127/9SwFPLj0CXMJ4rDbuc8yWQ2GUof5V6AZ.png","login_time_text":"","user_name":"MXY"},{"avatar":"/uploads/20191127/wYvlmBWNsyOHkMeSjF4uXgt9PTqaVzJ0.png","login_time_text":"","user_name":"NBFD-Alice"}]
         */

        private int total;
        private String per_page;
        private int current_page;
        private int last_page;
        private List<DataBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getPer_page() {
            return per_page;
        }

        public void setPer_page(String per_page) {
            this.per_page = per_page;
        }

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * avatar : /assets/img/avatar.png
             * login_time_text :
             * user_name :
             */

            private String avatar;
            private String login_time_text;
            private String user_name;

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getLogin_time_text() {
                return login_time_text;
            }

            public void setLogin_time_text(String login_time_text) {
                this.login_time_text = login_time_text;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }
        }
    }
}
