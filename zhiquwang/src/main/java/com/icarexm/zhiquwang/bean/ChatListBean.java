package com.icarexm.zhiquwang.bean;

import java.util.List;

public class ChatListBean {

    /**
     * code : 1
     * msg : success
     * data : [{"chat_id":23,"job_id":29,"user_id":3,"avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","nickname":"JinJuan","job_name":"达运包吃住美女多","enterprise_name":"达运电子精密有限公司","unread_num":3,"chat_record":"刚刚干活"}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * chat_id : 23
         * job_id : 29
         * user_id : 3
         * avatar : /uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg
         * nickname : JinJuan
         * job_name : 达运包吃住美女多
         * enterprise_name : 达运电子精密有限公司
         * unread_num : 3
         * chat_record : 刚刚干活
         */

        private int chat_id;
        private int job_id;
        private int user_id;
        private String avatar;
        private String nickname;
        private String job_name;
        private String enterprise_name;
        private int unread_num;
        private String chat_record;

        public int getChat_id() {
            return chat_id;
        }

        public void setChat_id(int chat_id) {
            this.chat_id = chat_id;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getEnterprise_name() {
            return enterprise_name;
        }

        public void setEnterprise_name(String enterprise_name) {
            this.enterprise_name = enterprise_name;
        }

        public int getUnread_num() {
            return unread_num;
        }

        public void setUnread_num(int unread_num) {
            this.unread_num = unread_num;
        }

        public String getChat_record() {
            return chat_record;
        }

        public void setChat_record(String chat_record) {
            this.chat_record = chat_record;
        }
    }
}
