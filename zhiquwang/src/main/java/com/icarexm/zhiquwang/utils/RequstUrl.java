package com.icarexm.zhiquwang.utils;

public class RequstUrl {
    public static class URL {
        //请求头
        public static String HOST = "http://192.168.1.22:8080";

        //登录接口
        public static String Login=HOST+"/api/login";

        //首页（轮播/筛选项）
        public static String Home=HOST+"/api/homePage";

        //首页数据
        public static  String HomeData=HOST+"/api/homeData";

        //招聘详情
        public static String JobDetail=HOST+"/api/jobDetail";

        //个人信息
        public static  String BasicsInfo=HOST+"/api/basicsInfo";

    }
}
