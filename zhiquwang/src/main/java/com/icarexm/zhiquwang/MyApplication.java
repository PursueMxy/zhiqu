package com.icarexm.zhiquwang;

import android.app.Application;

import com.icarexm.zhiquwang.utils.AppContUtils;
import com.icarexm.zhiquwang.utils.Density;
import com.icarexm.zhiquwang.wxapi.WXEntryActivity;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class MyApplication extends Application {

    public static IWXAPI iwxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        InitWeixin();
        Density.setDensity(this, 360);//375为UI提供设计图的宽度
        CrashReport.initCrashReport(getApplicationContext(),"27985782b5", true);
    }

    private void InitWeixin() {
        final IWXAPI api = WXAPIFactory.createWXAPI(getApplicationContext(), null,true);
        // 将该app注册到微信
        api.registerApp(AppContUtils.WX_APP_ID);
        iwxapi = WXEntryActivity.InitWeiXin(this, AppContUtils.WX_APP_ID);
    }
}
