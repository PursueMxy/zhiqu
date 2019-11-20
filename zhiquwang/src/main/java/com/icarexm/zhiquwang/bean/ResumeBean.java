package com.icarexm.zhiquwang.bean;

public class ResumeBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"mobile":"15306987579","avatar":"http://192.168.1.22:8080/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","sex":"","birth":"","city":"","education":"","personal_introduce":"","experience":"","user_name":"黑铁5","login_time_text":""}
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
         * avatar : http://192.168.1.22:8080/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg
         * sex :
         * birth :
         * city :
         * education :
         * personal_introduce :
         * experience :
         * user_name : 黑铁5
         * login_time_text :
         */

        private String mobile;
        private String avatar;
        private String sex;
        private String birth;
        private String city;
        private String education;
        private String personal_introduce;
        private String experience;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getPersonal_introduce() {
            return personal_introduce;
        }

        public void setPersonal_introduce(String personal_introduce) {
            this.personal_introduce = personal_introduce;
        }

        public String getExperience() {
            return experience;
        }

        public void setExperience(String experience) {
            this.experience = experience;
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
