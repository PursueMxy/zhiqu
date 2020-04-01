package com.icarexm.zhiquwang.bean;

import java.util.List;

public class ChatListBean {


    /**
     * code : 1
     * msg : success
     * data : [{"chat_id":42,"job_id":30,"user_id":3,"avatar":"/uploads/20200401/7fdfa118dd6ef5762aa778f4c0922f2a.jpg","nickname":"大帅测试","job_name":"测试职位测试职位测试职位","time":1585723744,"enterprise_name":"测试企业测试企业","unread_num":0,"chat_record":"我"},{"chat_id":46,"job_id":19,"user_id":3,"avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","nickname":"JinJuan","job_name":"天马包住月薪5000","time":1585723727,"enterprise_name":"厦门天马微电子股份有限公司","unread_num":0,"chat_record":"闺女"},{"chat_id":47,"job_id":13,"user_id":3,"avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","nickname":"JinJuan","job_name":"天马高薪招聘普工入职满30天立返500元","time":1585719406,"enterprise_name":"厦门天马微电子股份有限公司","unread_num":0,"chat_record":"/uploads/20200401/4086176b4d4aac2bda0d44d83a16c4a6.jpg"},{"chat_id":45,"job_id":26,"user_id":3,"avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","nickname":"JinJuan","job_name":"翔安乾照光电20元/时","time":1585717351,"enterprise_name":"厦门乾照光电股份有限公司","unread_num":0,"chat_record":"7777"},{"chat_id":43,"job_id":29,"user_id":3,"avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","nickname":"JinJuan","job_name":"达运包吃住美女多","time":1585716232,"enterprise_name":"达运电子精密有限公司","unread_num":0,"chat_record":"561156"}]
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
         * chat_id : 42
         * job_id : 30
         * user_id : 3
         * avatar : /uploads/20200401/7fdfa118dd6ef5762aa778f4c0922f2a.jpg
         * nickname : 大帅测试
         * job_name : 测试职位测试职位测试职位
         * time : 1585723744
         * enterprise_name : 测试企业测试企业
         * unread_num : 0
         * chat_record : 我
         */

        private int chat_id;
        private int job_id;
        private int user_id;
        private String avatar;
        private String nickname;
        private String job_name;
        private int time;
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

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
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
