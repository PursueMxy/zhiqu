package com.icarexm.zhiquwang.bean;

import java.util.List;

public class ResumeBean {


    /**
     * code : 1
     * msg : 获取成功
     * data : {"money":"50000.00","mobile":"15306987579","avatar":"/uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg","sex":"男","birth":"1993年01","city":"福建厦门市","education":"本科","personal_introduce":"谢谢唱个歌哼哼唧唧","experience":[{"company_name":"add","job_content":"Sdf","job_end":"2019.12.30","job_start":"2019.12.30","leave_cause":"Sdf"},{"company_name":"2558","job_content":"8855","job_end":"2019-12-30","job_start":"2019-12-30","leave_cause":"996"}],"user_name":"黑铁5","age":"27岁","login_time_text":""}
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
         * money : 50000.00
         * mobile : 15306987579
         * avatar : /uploads/20191206/41f13f8bee25b1d28ca8ec6e5966106c.jpg
         * sex : 男
         * birth : 1993年01
         * city : 福建厦门市
         * education : 本科
         * personal_introduce : 谢谢唱个歌哼哼唧唧
         * experience : [{"company_name":"add","job_content":"Sdf","job_end":"2019.12.30","job_start":"2019.12.30","leave_cause":"Sdf"},{"company_name":"2558","job_content":"8855","job_end":"2019-12-30","job_start":"2019-12-30","leave_cause":"996"}]
         * user_name : 黑铁5
         * age : 27岁
         * login_time_text :
         */

        private String money;
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

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

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
             * company_name : add
             * job_content : Sdf
             * job_end : 2019.12.30
             * job_start : 2019.12.30
             * leave_cause : Sdf
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
