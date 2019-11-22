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

        //上传图片
        public static String UploadImg=HOST+"/api/uploadImg";

        //修改个人信息
        public static String SetUsername=HOST+"/api/setUsername";

        //修改密码
        public static String EditPass=HOST+"/api/editPass";

        //关于我们
        public static  String About=HOST+"/api/aboutUs";

        //意见反馈
        public static  String SbmitIdea=HOST+"/api/submitIdea";

        //我的提现
        public static  String MyBalance=HOST+"/api/myBalance";

        //我的提现
        public static  String addBank=HOST+"/api/addBank";

        //银行卡列表
        public static String  myBank=HOST+"/api/myBank";

        //提现
        public static String doWithdrawal=HOST+"/api/doWithdrawal";

        //提现列表
        public static  String SeeFund=HOST+"/api/seeFund";

        //分销团队首页
        public static String TeamInfo=HOST+"/api/teamInfo";

        //实名认证进入
        public static String AutoNym=HOST+"/api/autoNym";

        //实名信息上传
        public static String DoAutoNym=HOST+"/api/doAutoNym";

        //我的简历
        public static String MineInfo=HOST+"/api/mineInfo";

        //保存简历
        public static  String AddResume=HOST+"/api/addResume";

        //进入商务合作
        public static String Cooperation=HOST+"/api/cooperation";

        //添加合作需求
        public static  String doCooperation=HOST+"/api/doCooperation";

        //附件门店
        public static String NearbyStores=HOST+"/api/nearbyStores";

        //门店报名
        public static String toApplication=HOST+"/api/toApplication";

        //点击报名
        public static String applicationInfo=HOST+"/api/applicationInfo";

        //打卡页面
        public static String playInfo=HOST+"/api/playInfo";

        //发送短信
        public static String sendMsg=HOST+"/api/sendMsg";

        //找回密码
        public static String findPass=HOST+"/api/findPass";

        //注册
        public static String Register=HOST+"/api/register";

    }
}
