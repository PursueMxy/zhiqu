package com.icarexm.zhiquwang.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class MxyUtils {
    /**
     * dp转px
     *
     * @param context
     * @param dp
     * @return
     */
    public static  int dpToPx(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) ((dp * displayMetrics.density) + 0.5f);
    }
    /**
     * 获得资源 dimens (dp)
     *
     * @param context
     * @param id      资源id
     * @return
     */
    public static float getDimens(Context context, int id) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        float px = context.getResources().getDimension(id);
        return px / dm.density;
    }
}
