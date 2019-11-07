// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.icarexm.zhiquwang.custview.mywheel;

import android.view.MotionEvent;

final class WheelViewGestureListener extends android.view.GestureDetector.SimpleOnGestureListener {

    final MyWheelView wheelView;

    WheelViewGestureListener(MyWheelView wheelview) {
        wheelView = wheelview;
    }

    @Override
    public final boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        wheelView.scrollBy(velocityY);
        return true;
    }
}
