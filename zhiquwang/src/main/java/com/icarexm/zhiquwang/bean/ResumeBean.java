package com.icarexm.zhiquwang.bean;

import java.util.List;

public class ResumeBean {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"mobile":"15306987579","avatar":"http://192.168.1.22:8080/uploads/20191119/d9a7d60b9ccbbcc59e2261ded6117d1b.jpg","sex":"男","birth":"1993年01","city":"福建厦门市","education":"本科","personal_introduce":"5877","experience":[{"company_name":"2588","job_content":"丰富","job_end":"2017-11-21","job_start":"2016-11-21","leave_cause":"1"},{"company_name":"点的","job_content":"2588","job_end":"2018-11-21","job_start":"2017-11-21","leave_cause":"f"}],"user_name":"黑铁5","age":"26岁","login_time_text":""}
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
         * sex : 男
         * birth : 1993年01
         * city : 福建厦门市
         * education : 本科
         * personal_introduce : 5877
         * experience : [{"company_name":"2588","job_content":"丰富","job_end":"2017-11-21","job_start":"2016-11-21","leave_cause":"1"},{"company_name":"点的","job_content":"2588","job_end":"2018-11-21","job_start":"2017-11-21","leave_cause":"f"}]
         * user_name : 黑铁5
         * age : 26岁
         * login_time_text :
         */

        private String mobile;
        private String avatar;
        private String sex;
        private String birth;
        private String city;
        private String education;
        private String personal_introduce;
        private String user_name;
        private String age;
        private String login_time_text;
        private List<ExperienceBean> experience;

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

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getLogin_time_text() {
            return login_time_text;
        }

        public void setLogin_time_text(String login_time_text) {
            this.login_time_text = login_time_text;
        }

        public List<ExperienceBean> getExperience() {
            return experience;
        }

        public void setExperience(List<ExperienceBean> experience) {
            this.experience = experience;
        }

        public static class ExperienceBean {
            /**
             * company_name : 2588
             * job_content : 丰富
             * job_end : 2017-11-21
             * job_start : 2016-11-21
             * leave_cause : 1
             */

            private String company_name;
            private String job_content;
            private String job_end;
            private String job_start;
            private String leave_cause;

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getJob_content() {
                return job_content;
            }

            public void setJob_content(String job_content) {
                this.job_content = job_content;
            }

            public String getJob_end() {
                return job_end;
            }

            public void setJob_end(String job_end) {
                this.job_end = job_end;
            }

            public String getJob_start() {
                return job_start;
            }

            public void setJob_start(String job_start) {
                this.job_start = job_start;
            }

            public String getLeave_cause() {
                return leave_cause;
            }

            public void setLeave_cause(String leave_cause) {
                this.leave_cause = leave_cause;
            }
        }
    }
}
