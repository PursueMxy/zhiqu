package com.icarexm.zhiquwang.custview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import com.icarexm.zhiquwang.R;

public class IndicatorView extends View {
    Paint paint;
    private int mIndicatorHeight= R.dimen.dp_5; // 滑动指示器高度
    public IndicatorView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.GRAY);
    }

    public void changeColor(int color){
        paint.setColor(color);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(16, mIndicatorHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(8, mIndicatorHeight/2, 4, paint);
    }


}
