package com.icarexm.zhiquwang.bean;

import java.util.List;

public class JobDetailBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"job_id":1,"job_name":"第一个发布职位","have_video":2,"label_arr":[{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}],"label_price":"返现//*","salary_hour":"25.00","salary_other":"1、没有休息日\r\n2、每天十六个小时工作时长\r\n3、调试","need_sex":"女","age_explain":"男24-30 女25-30","education":"本科","work_year":10,"need_other":"1、其他需求第一点\r\n2、其他需求第二点\r\n3、其他需求第三点\r\n","work_describe":"1、工作描述第一点\r\n2、工作描述第二点\r\n3、工作描述第三点","longitude":"118.110220","latitude":"24.490474","address":"福建省厦门市思明区筼筜街道仙岳路163古石雕大观园","enterprise_name":"冤大头一号","introduce":"在乎科技成立于2015年，总部位于厦门，专注于移动互联网应用解决方案领域，以互联网技术驱动为核心，为客户提供专业高效的产品与技术服务，助力企业的互联网+转型，通过信息化提升企业运作效率与竞争力；我们崇尚互联网产品思维，坚信技术创造更美好的未来，不断的去发现和创造更符合用户需求的产品，致力于成为行业领先的移动互联网应用方案服务商","img_arr":["http://192.168.1.22:8080/uploads/20191108/9b80fb829010bcedd1c857ba8ab6757d.mp4","http://192.168.1.22:8080/uploads/20191108/d1959ebc21bf16f918252cff0985bf24.png","http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png","http://192.168.1.22:8080/uploads/20191108/7a3dd7ea314c6279e4fd77ed95bc99e1.jpg"],"salary":"500.00-500.00","age":"18-35"}
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
         * job_id : 1
         * job_name : 第一个发布职位
         * have_video : 2
         * label_arr : [{"label_name":"猜不透的结局"},{"label_name":"皇家特聘"}]
         * label_price : 返现//*
         * salary_hour : 25.00
         * salary_other : 1、没有休息日
         2、每天十六个小时工作时长
         3、调试
         * need_sex : 女
         * age_explain : 男24-30 女25-30
         * education : 本科
         * work_year : 10
         * need_other : 1、其他需求第一点
         2、其他需求第二点
         3、其他需求第三点
         * work_describe : 1、工作描述第一点
         2、工作描述第二点
         3、工作描述第三点
         * longitude : 118.110220
         * latitude : 24.490474
         * address : 福建省厦门市思明区筼筜街道仙岳路163古石雕大观园
         * enterprise_name : 冤大头一号
         * introduce : 在乎科技成立于2015年，总部位于厦门，专注于移动互联网应用解决方案领域，以互联网技术驱动为核心，为客户提供专业高效的产品与技术服务，助力企业的互联网+转型，通过信息化提升企业运作效率与竞争力；我们崇尚互联网产品思维，坚信技术创造更美好的未来，不断的去发现和创造更符合用户需求的产品，致力于成为行业领先的移动互联网应用方案服务商
         * img_arr : ["http://192.168.1.22:8080/uploads/20191108/9b80fb829010bcedd1c857ba8ab6757d.mp4","http://192.168.1.22:8080/uploads/20191108/d1959ebc21bf16f918252cff0985bf24.png","http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png","http://192.168.1.22:8080/uploads/20191108/7a3dd7ea314c6279e4fd77ed95bc99e1.jpg"]
         * salary : 500.00-500.00
         * age : 18-35
         */

        private int job_id;
        private String job_name;
        private int have_video;
        private String label_price;
        private String salary_hour;
        private String salary_other;
        private String need_sex;
        private String age_explain;
        private String education;
        private int work_year;
        private String need_other;
        private String work_describe;
        private String longitude;
        private String latitude;
        private String address;
        private String enterprise_name;
        private String introduce;
        private String salary;
        private String age;
        private List<LabelArrBean> label_arr;
        private List<String> img_arr;

        public int getJob_id() {
            return job_id;
        }

        public void setJob_id(int job_id) {
            this.job_id = job_id;
        }

        public String getJob_name() {
            return job_name;
        }

        public void setJob_name(String job_name) {
            this.job_name = job_name;
        }

        public int getHave_video() {
            return have_video;
        }

        public void setHave_video(int have_video) {
            this.have_video = have_video;
        }

        public String getLabel_price() {
            return label_price;
        }

        public void setLabel_price(String label_price) {
            this.label_price = label_price;
        }

        public String getSalary_hour() {
            return salary_hour;
        }

        public void setSalary_hour(String salary_hour) {
            this.salary_hour = salary_hour;
        }

        public String getSalary_other() {
            return salary_other;
        }

        public void setSalary_other(String salary_other) {
            this.salary_other = salary_other;
        }

        public String getNeed_sex() {
            return need_sex;
        }

        public void setNeed_sex(String need_sex) {
            this.need_sex = need_sex;
        }

        public String getAge_explain() {
            return age_explain;
        }

        public void setAge_explain(String age_explain) {
            this.age_explain = age_explain;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public int getWork_year() {
            return work_year;
        }

        public void setWork_year(int work_year) {
            this.work_year = work_year;
        }

        public String getNeed_other() {
            return need_other;
        }

        public void setNeed_other(String need_other) {
            this.need_other = need_other;
        }

        public String getWork_describe() {
            return work_describe;
        }

        public void setWork_describe(String work_describe) {
            this.work_describe = work_describe;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getEnterprise_name() {
            return enterprise_name;
        }

        public void setEnterprise_name(String enterprise_name) {
            this.enterprise_name = enterprise_name;
        }

        public String getIntroduce() {
            return introduce;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public List<LabelArrBean> getLabel_arr() {
            return label_arr;
        }

        public void setLabel_arr(List<LabelArrBean> label_arr) {
            this.label_arr = label_arr;
        }

        public List<String> getImg_arr() {
            return img_arr;
        }

        public void setImg_arr(List<String> img_arr) {
            this.img_arr = img_arr;
        }

        public static class LabelArrBean {
            /**
             * label_name : 猜不透的结局
             */

            private String label_name;

            public String getLabel_name() {
                return label_name;
            }

            public void setLabel_name(String label_name) {
                this.label_name = label_name;
            }
        }
    }
}
