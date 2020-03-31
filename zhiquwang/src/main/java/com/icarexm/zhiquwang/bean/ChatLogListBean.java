package com.icarexm.zhiquwang.bean;

import java.util.List;

public class ChatLogListBean {

    /**
     * code : 1
     * msg : success
     * data : {"total":90,"per_page":"3","current_page":1,"last_page":30,"data":[{"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","id":609,"chat_id":23,"user_id":3,"admin_id":1,"job_id":29,"time":1585660805,"content":"/uploads/20200331/4086176b4d4aac2bda0d44d83a16c4a6.jpg","type":2,"status":1,"create_time":"2020-03-31 21:20:05","side":1},{"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","id":608,"chat_id":23,"user_id":3,"admin_id":1,"job_id":29,"time":1585660446,"content":"8555","type":1,"status":1,"create_time":"2020-03-31 21:14:06","side":2},{"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","id":607,"chat_id":23,"user_id":3,"admin_id":1,"job_id":29,"time":1585660443,"content":"55666","type":1,"status":1,"create_time":"2020-03-31 21:14:03","side":2}]}
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
         * total : 90
         * per_page : 3
         * current_page : 1
         * last_page : 30
         * data : [{"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","id":609,"chat_id":23,"user_id":3,"admin_id":1,"job_id":29,"time":1585660805,"content":"/uploads/20200331/4086176b4d4aac2bda0d44d83a16c4a6.jpg","type":2,"status":1,"create_time":"2020-03-31 21:20:05","side":1},{"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","id":608,"chat_id":23,"user_id":3,"admin_id":1,"job_id":29,"time":1585660446,"content":"8555","type":1,"status":1,"create_time":"2020-03-31 21:14:06","side":2},{"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_avatar":"/uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg","id":607,"chat_id":23,"user_id":3,"admin_id":1,"job_id":29,"time":1585660443,"content":"55666","type":1,"status":1,"create_time":"2020-03-31 21:14:03","side":2}]
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
             * user_avatar : /uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg
             * admin_avatar : /uploads/20200331/a8f6be4db006dcdbc32c51a203d03f8a.jpg
             * id : 609
             * chat_id : 23
             * user_id : 3
             * admin_id : 1
             * job_id : 29
             * time : 1585660805
             * content : /uploads/20200331/4086176b4d4aac2bda0d44d83a16c4a6.jpg
             * type : 2
             * status : 1
             * create_time : 2020-03-31 21:20:05
             * side : 1
             */

            private String user_avatar;
            private String admin_avatar;
            private int id;
            private int chat_id;
            private int user_id;
            private int admin_id;
            private int job_id;
            private int time;
            private String content;
            private int type;
            private int status;
            private String create_time;
            private int side;

            public String getUser_avatar() {
                return user_avatar;
            }

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public String getAdmin_avatar() {
                return admin_avatar;
            }

            public void setAdmin_avatar(String admin_avatar) {
                this.admin_avatar = admin_avatar;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

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

            public int getAdmin_id() {
                return admin_id;
            }

            public void setAdmin_id(int admin_id) {
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getSide() {
                return side;
            }

            public void setSide(int side) {
                this.side = side;
            }
        }
    }
}
