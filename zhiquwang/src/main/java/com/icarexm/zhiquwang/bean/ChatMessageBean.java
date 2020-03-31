package com.icarexm.zhiquwang.bean;

public class ChatMessageBean {


    /**
     * nameValuePairs : {"chat_id":13,"user_id":3,"admin_id":"1","job_id":29,"time":1585652699,"content":"1111111111","type":1,"avatar":"/assets/img/avatar.png","side":2}
     */

    private NameValuePairsBean nameValuePairs;

    public NameValuePairsBean getNameValuePairs() {
        return nameValuePairs;
    }

    public void setNameValuePairs(NameValuePairsBean nameValuePairs) {
        this.nameValuePairs = nameValuePairs;
    }

    public static class NameValuePairsBean {
        /**
         * chat_id : 13
         * user_id : 3
         * admin_id : 1
         * job_id : 29
         * time : 1585652699
         * content : 1111111111
         * type : 1
         * avatar : /assets/img/avatar.png
         * side : 2
         */

        private int chat_id;
        private int user_id;
        private String admin_id;
        private int job_id;
        private int time;
        private String content;
        private int type;
        private String avatar;
        private int side;

        public int getChat_id() {
            return chat_id;
        }

        public void setChat_id(int chat_id) {
            this.chat_id = chat_id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getSide() {
            return side;
        }

        public void setSide(int side) {
            this.side = side;
        }

        public NameValuePairsBean(int chat_id, int user_id, String admin_id, int job_id, int time, String content, int type, String avatar, int side) {
            this.chat_id = chat_id;
            this.user_id = user_id;
            this.admin_id = admin_id;
            this.job_id = job_id;
            this.time = time;
            this.content = content;
            this.type = type;
            this.avatar = avatar;
            this.side = side;
        }
    }

}
