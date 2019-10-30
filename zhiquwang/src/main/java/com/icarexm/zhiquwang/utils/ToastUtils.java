package com.icarexm.zhiquwang.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by 微软中国 on 2018/10/27.
 */

public class ToastUtils {
    private static Toast toast;
    public static void showToast(Context context, String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }
}
