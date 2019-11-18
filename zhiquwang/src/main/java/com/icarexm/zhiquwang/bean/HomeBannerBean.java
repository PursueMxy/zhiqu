package com.icarexm.zhiquwang.bean;

import java.util.List;

public class HomeBannerBean {

    /**
     * code : 1
     * msg : 获取成功
     * data : {"banner_list":[{"banner_id":2,"banner_name":"首页轮播图2","banner_url":"http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png","skip_url":""},{"banner_id":1,"banner_name":"首页轮播图1","banner_url":"http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png","skip_url":""}],"zone_list":[{"zone_id":1,"zone_name":"名企招聘","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/0f4a32254661ba8ba73594cb11c674b8.png"},{"zone_id":2,"zone_name":"高返费专区","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/834915c56be831bc58aab03f96feab28.png"},{"zone_id":4,"zone_name":"高价小时工","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/e4d78670f38b8029d0ccf82143ace3b7.png"},{"zone_id":5,"zone_name":"高级蓝领","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/1f1bc13f252778eef4408cb99bda896b.png"}],"option_list":{"area_list":[{"area_id":350203,"area_name":"思明区"},{"area_id":350205,"area_name":"海沧区"},{"area_id":350206,"area_name":"湖里区"},{"area_id":350211,"area_name":"集美区"},{"area_id":350212,"area_name":"同安区"},{"area_id":350213,"area_name":"翔安区"}],"salary":[{"salary_id":8,"salary_value":"5000-8000"},{"salary_id":4,"salary_value":"0-3000"}],"age":[{"age_id":9,"age_value":"38-54"},{"age_id":5,"age_value":"20-35"}],"vocation":[{"vocation_id":6,"vocation_value":"计算机"}],"environment":[{"environment_id":7,"environment_value":"超大海景办公室"}]}}
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
         * banner_list : [{"banner_id":2,"banner_name":"首页轮播图2","banner_url":"http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png","skip_url":""},{"banner_id":1,"banner_name":"首页轮播图1","banner_url":"http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png","skip_url":""}]
         * zone_list : [{"zone_id":1,"zone_name":"名企招聘","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/0f4a32254661ba8ba73594cb11c674b8.png"},{"zone_id":2,"zone_name":"高返费专区","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/834915c56be831bc58aab03f96feab28.png"},{"zone_id":4,"zone_name":"高价小时工","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/e4d78670f38b8029d0ccf82143ace3b7.png"},{"zone_id":5,"zone_name":"高级蓝领","zone_icon":"http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/1f1bc13f252778eef4408cb99bda896b.png"}]
         * option_list : {"area_list":[{"area_id":350203,"area_name":"思明区"},{"area_id":350205,"area_name":"海沧区"},{"area_id":350206,"area_name":"湖里区"},{"area_id":350211,"area_name":"集美区"},{"area_id":350212,"area_name":"同安区"},{"area_id":350213,"area_name":"翔安区"}],"salary":[{"salary_id":8,"salary_value":"5000-8000"},{"salary_id":4,"salary_value":"0-3000"}],"age":[{"age_id":9,"age_value":"38-54"},{"age_id":5,"age_value":"20-35"}],"vocation":[{"vocation_id":6,"vocation_value":"计算机"}],"environment":[{"environment_id":7,"environment_value":"超大海景办公室"}]}
         */

        private OptionListBean option_list;
        private List<BannerListBean> banner_list;
        private List<ZoneListBean> zone_list;

        public OptionListBean getOption_list() {
            return option_list;
        }

        public void setOption_list(OptionListBean option_list) {
            this.option_list = option_list;
        }

        public List<BannerListBean> getBanner_list() {
            return banner_list;
        }

        public void setBanner_list(List<BannerListBean> banner_list) {
            this.banner_list = banner_list;
        }

        public List<ZoneListBean> getZone_list() {
            return zone_list;
        }

        public void setZone_list(List<ZoneListBean> zone_list) {
            this.zone_list = zone_list;
        }

        public static class OptionListBean {
            private List<AreaListBean> area_list;
            private List<SalaryBean> salary;
            private List<AgeBean> age;
            private List<VocationBean> vocation;
            private List<EnvironmentBean> environment;

            public List<AreaListBean> getArea_list() {
                return area_list;
            }

            public void setArea_list(List<AreaListBean> area_list) {
                this.area_list = area_list;
            }

            public List<SalaryBean> getSalary() {
                return salary;
            }

            public void setSalary(List<SalaryBean> salary) {
                this.salary = salary;
            }

            public List<AgeBean> getAge() {
                return age;
            }

            public void setAge(List<AgeBean> age) {
                this.age = age;
            }

            public List<VocationBean> getVocation() {
                return vocation;
            }

            public void setVocation(List<VocationBean> vocation) {
                this.vocation = vocation;
            }

            public List<EnvironmentBean> getEnvironment() {
                return environment;
            }

            public void setEnvironment(List<EnvironmentBean> environment) {
                this.environment = environment;
            }

            public static class AreaListBean {
                /**
                 * area_id : 350203
                 * area_name : 思明区
                 */

                private int area_id;
                private String area_name;

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public String getArea_name() {
                    return area_name;
                }

                public void setArea_name(String area_name) {
                    this.area_name = area_name;
                }
            }

            public static class SalaryBean {
                /**
                 * salary_id : 8
                 * salary_value : 5000-8000
                 */

                private int salary_id;
                private String salary_value;

                public int getSalary_id() {
                    return salary_id;
                }

                public void setSalary_id(int salary_id) {
                    this.salary_id = salary_id;
                }

                public String getSalary_value() {
                    return salary_value;
                }

                public void setSalary_value(String salary_value) {
                    this.salary_value = salary_value;
                }
            }

            public static class AgeBean {
                /**
                 * age_id : 9
                 * age_value : 38-54
                 */

                private int age_id;
                private String age_value;

                public int getAge_id() {
                    return age_id;
                }

                public void setAge_id(int age_id) {
                    this.age_id = age_id;
                }

                public String getAge_value() {
                    return age_value;
                }

                public void setAge_value(String age_value) {
                    this.age_value = age_value;
                }
            }

            public static class VocationBean {
                /**
                 * vocation_id : 6
                 * vocation_value : 计算机
                 */

                private int vocation_id;
                private String vocation_value;

                public int getVocation_id() {
                    return vocation_id;
                }

                public void setVocation_id(int vocation_id) {
                    this.vocation_id = vocation_id;
                }

                public String getVocation_value() {
                    return vocation_value;
                }

                public void setVocation_value(String vocation_value) {
                    this.vocation_value = vocation_value;
                }
            }

            public static class EnvironmentBean {
                /**
                 * environment_id : 7
                 * environment_value : 超大海景办公室
                 */

                private int environment_id;
                private String environment_value;

                public int getEnvironment_id() {
                    return environment_id;
                }

                public void setEnvironment_id(int environment_id) {
                    this.environment_id = environment_id;
                }

                public String getEnvironment_value() {
                    return environment_value;
                }

                public void setEnvironment_value(String environment_value) {
                    this.environment_value = environment_value;
                }
            }
        }

        public static class BannerListBean {
            /**
             * banner_id : 2
             * banner_name : 首页轮播图2
             * banner_url : http://192.168.1.22:8080/uploads/20191101/09a2c49ee61c519ae213b5bf1598bf24.png
             * skip_url :
             */

            private int banner_id;
            private String banner_name;
            private String banner_url;
            private String skip_url;

            public int getBanner_id() {
                return banner_id;
            }

            public void setBanner_id(int banner_id) {
                this.banner_id = banner_id;
            }

            public String getBanner_name() {
                return banner_name;
            }

            public void setBanner_name(String banner_name) {
                this.banner_name = banner_name;
            }

            public String getBanner_url() {
                return banner_url;
            }

            public void setBanner_url(String banner_url) {
                this.banner_url = banner_url;
            }

            public String getSkip_url() {
                return skip_url;
            }

            public void setSkip_url(String skip_url) {
                this.skip_url = skip_url;
            }
        }

        public static class ZoneListBean {
            /**
             * zone_id : 1
             * zone_name : 名企招聘
             * zone_icon : http://192.168.1.22:8080http://192.168.1.22:8080/uploads/20191030/0f4a32254661ba8ba73594cb11c674b8.png
             */

            private int zone_id;
            private String zone_name;
            private String zone_icon;

            public int getZone_id() {
                return zone_id;
            }

            public void setZone_id(int zone_id) {
                this.zone_id = zone_id;
            }

            public String getZone_name() {
                return zone_name;
            }

            public void setZone_name(String zone_name) {
                this.zone_name = zone_name;
            }

            public String getZone_icon() {
                return zone_icon;
            }

            public void setZone_icon(String zone_icon) {
                this.zone_icon = zone_icon;
            }
        }
    }
}
