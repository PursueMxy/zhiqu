package com.icarexm.zhiquwang.bean;

public class CertificationBean {

    /**
     * code : 1
     * msg : 成功获取
     * data : {"audit":1,"info":{"user_id":3,"real_name":"mxy","card_num":"123456898","card_front":"/uploads/20191120/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","card_reverse":"/uploads/20191120/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","audit":1,"create_time":"2019-11-20 14:14:17","update_time":"2019-11-20 14:14:17"}}
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
         * audit : 1
         * info : {"user_id":3,"real_name":"mxy","card_num":"123456898","card_front":"/uploads/20191120/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","card_reverse":"/uploads/20191120/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","audit":1,"create_time":"2019-11-20 14:14:17","update_time":"2019-11-20 14:14:17"}
         */

        private int audit;
        private InfoBean info;

        public int getAudit() {
            return audit;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * user_id : 3
             * real_name : mxy
             * card_num : 123456898
             * card_front : /uploads/20191120/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg
             * card_reverse : /uploads/20191120/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg
             * audit : 1
             * create_time : 2019-11-20 14:14:17
             * update_time : 2019-11-20 14:14:17
             */

            private int user_id;
            private String real_name;
            private String card_num;
            private String card_front;
            private String card_reverse;
            private int audit;
            private String create_time;
            private String update_time;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getCard_num() {
                return card_num;
            }

            public void setCard_num(String card_num) {
                this.card_num = card_num;
            }

            public String getCard_front() {
                return card_front;
            }

            public void setCard_front(String card_front) {
                this.card_front = card_front;
            }

            public String getCard_reverse() {
                return card_reverse;
            }

            public void setCard_reverse(String card_reverse) {
                this.card_reverse = card_reverse;
            }

            public int getAudit() {
                return audit;
            }

            public void setAudit(int audit) {
                this.audit = audit;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getUpdate_time() {
                return update_time;
            }

            public void setUpdate_time(String update_time) {
                this.update_time = update_time;
            }
        }
    }
}
