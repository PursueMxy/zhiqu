package com.icarexm.zhiquwang;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.icarexm.zhiquwang.view.activity.HomeActivity;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.helper.Logger;

public class NotificationClickReceiver extends BroadcastReceiver {
    private static final String TAG = "极光推送";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String title = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
        Log.i("TAG", "userClick:我被点击啦！！！ "+title);
        if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Logger.d(TAG, "用户点击打开了通知");
            Intent newIntent = new Intent(context, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           context.startActivity(newIntent);

        }
    }
}
