package com.icarexm.zhiquwang;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.danikula.videocache.HttpProxyCacheServer;
import com.icarexm.zhiquwang.chatroom.MainChatRoom;
import com.icarexm.zhiquwang.utils.AppContUtils;
import com.icarexm.zhiquwang.utils.Density;
import com.icarexm.zhiquwang.wxapi.WXEntryActivity;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.litepal.LitePal;

import cn.jpush.android.api.JPushInterface;

public class MyApplication extends Application {

    public static IWXAPI iwxapi;
    //全局初始化一个本地代理服务器
    private HttpProxyCacheServer proxy;
    @Override
    public void onCreate() {
        super.onCreate();
        //设置字体不随系统变大而变大
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        InitWeixin();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        LitePal.initialize(this);
        Density.setDensity(this, 360);
        CrashReport.initCrashReport(getApplicationContext(),"27985782b5", false);
        MainChatRoom.init();
    }

    private void InitWeixin() {
        final IWXAPI api = WXAPIFactory.createWXAPI(getApplicationContext(), null,false);
        // 将该app注册到微信
        api.registerApp(AppContUtils.WX_APP_ID);
        iwxapi = WXEntryActivity.InitWeiXin(this, AppContUtils.WX_APP_ID);
    }

    public static HttpProxyCacheServer getProxy(Context context) {
        MyApplication app = ( MyApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer(this);
    }


}
