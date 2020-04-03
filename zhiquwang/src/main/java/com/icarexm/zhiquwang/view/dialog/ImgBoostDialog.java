package com.icarexm.zhiquwang.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.StatusBarUtil;
import com.icarexm.zhiquwang.utils.ToastUtils;

import me.panpf.sketch.SketchImageView;

public class ImgBoostDialog extends Dialog {

    private SketchImageView boost_img;
    private String url;
    private Context mContext;

    public ImgBoostDialog(@NonNull Context context,String url) {
        super(context);
        this.url=url;
        this.mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_img_boost);
        Window window = getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams params = window.getAttributes();
        int height = StatusBarUtil.dip2px(mContext, 600);
        params.gravity = Gravity.CENTER; // 显示在底部
        params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
        params.height =height;
        window.setAttributes(params);
        boost_img = findViewById(R.id.dialog_boost_img);
        boost_img.displayImage(url);
        boost_img.setZoomEnabled(true);
        boost_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               dismiss();
            }
        });
    }

}
