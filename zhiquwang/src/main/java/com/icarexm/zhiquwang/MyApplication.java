package com.icarexm.zhiquwang;

import android.app.Application;
import android.content.Context;

import com.danikula.videocache.HttpProxyCacheServer;
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
        InitWeixin();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        LitePal.initialize(this);
        Density.setDensity(this, 360);//375为UI提供设计图的宽度
        CrashReport.initCrashReport(getApplicationContext(),"27985782b5", false);
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
