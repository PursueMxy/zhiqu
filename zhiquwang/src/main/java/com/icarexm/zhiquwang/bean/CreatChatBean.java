package com.icarexm.zhiquwang.bean;

import java.util.List;

public class CreatChatBean {


    /**
     * code : 1
     * msg : success
     * data : {"job_zone":1,"salary_structure":"底薪+加班费+绩效+岗位津贴","job_name":"达运包吃住美女多","label_arr":[{"label_name":"电子行业"}],"salary_start":"3500.00","salary_end":"5000.00","salary_hour":"底薪+加班费+绩效+岗位津贴","age_start":18,"age_end":35,"admin_id":1,"job_id":29,"age":"18-35","chat_id":13,"user_id":3,"user_avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","admin_name":"JinJuan","word":[{"id":1,"word":"名企","create_time":"2020-03-24 13:47:56"},{"id":2,"word":"大厂","create_time":"2020-03-24 13:48:03"}]}
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
         * job_zone : 1
         * salary_structure : 底薪+加班费+绩效+岗位津贴
         * job_name : 达运包吃住美女多
         * label_arr : [{"label_name":"电子行业"}]
         * salary_start : 3500.00
         * salary_end : 5000.00
         * salary_hour : 底薪+加班费+绩效+岗位津贴
         * age_start : 18
         * age_end : 35
         * admin_id : 1
         * job_id : 29
         * age : 18-35
         * chat_id : 13
         * user_id : 3
         * user_avatar : /uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg
         * admin_name : JinJuan
         * word : [{"id":1,"word":"名企","create_time":"2020-03-24 13:47:56"},{"id":2,"word":"大厂","create_time":"2020-03-24 13:48:03"}]
         */

        private int job_zone;
        private String salary_structure;
        private String job_name;
        private String salary_start;
        private String salary_end;
        private String salary_hour;
        private int age_start;
        private int age_end;
        private int admin_id;
        private int job_id;
        private String age;
        private int chat_id;
        private int user_id;
        private String user_avatar;
        private String admin_name;
        private List<LabelArrBean> label_arr;
        private List<WordBean> word;

        public int getJob_zone() {
            return job_zone;
        }

        public void setJob_zone(int job_zone) {
            this.job_zone = job_zone;
        }

        public String getSalary_structure() {
            return salary_structure;
        }

        public void setSalary_structure(String salary_structure) {
            this.salary_structure = salary_structure;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public String getSalary_start() {
            return salary_start;
        }

        public void setSalary_start(String salary_start) {
            this.salary_start = salary_start;
        }

        public String getSalary_end() {
            return salary_end;
        }

        public void setSalary_end(String salary_end) {
            this.salary_end = salary_end;
        }

        public String getSalary_hour() {
            return salary_hour;
        }

        public void setSalary_hour(String salary_hour) {
            this.salary_hour = salary_hour;
        }

        public int getAge_start() {
            return age_start;
        }

        public void setAge_start(int age_start) {
            this.age_start = age_start;
        }

        public int getAge_end() {
            return age_end;
        }

        public void setAge_end(int age_end) {
            this.age_end = age_end;
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

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
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

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public String getAdmin_name() {
            return admin_name;
        }

        public void setAdmin_name(String admin_name) {
            this.admin_name = admin_name;
        }

        public List<LabelArrBean> getLabel_arr() {
            return label_arr;
        }

        public void setLabel_arr(List<LabelArrBean> label_arr) {
            this.label_arr = label_arr;
        }

        public List<WordBean> getWord() {
            return word;
        }

        public void setWord(List<WordBean> word) {
            this.word = word;
        }

        public static class LabelArrBean {
            /**
             * label_name : 电子行业
             */

            private String label_name;

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }
        }

        public static class WordBean {
            /**
             * id : 1
             * word : 名企
             * create_time : 2020-03-24 13:47:56
             */

            private int id;
            private String word;
            private String create_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
