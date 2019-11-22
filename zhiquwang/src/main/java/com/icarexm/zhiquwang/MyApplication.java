package com.icarexm.zhiquwang;

import android.app.Application;

import com.icarexm.zhiquwang.utils.Density;
import com.tencent.bugly.crashreport.CrashReport;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Density.setDensity(this, 360);//375为UI提供设计图的宽度
        CrashReport.initCrashReport(getApplicationContext(),"27985782b5", true);
    }
}
