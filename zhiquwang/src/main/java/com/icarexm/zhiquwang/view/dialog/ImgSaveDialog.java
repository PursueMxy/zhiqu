package com.icarexm.zhiquwang.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.utils.StatusBarUtil;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.panpf.sketch.SketchImageView;

public class ImgSaveDialog extends Dialog {

    private SketchImageView boost_img;
    private String url;
    private Context mContext;

    public ImgSaveDialog(@NonNull Activity context, String url) {
        super(context);
        this.url=url;
        this.mContext=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_img_save);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        params.gravity = Gravity.BOTTOM; // 显示在底部
        params.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度填充满屏
        window.setAttributes(params);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.dialog_img_save_btn_save,R.id.dialog_img_save_btn_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.dialog_img_save_btn_save:
                Glide.with(mContext)
                        .asBitmap()
                        .load(url)
                        .into(new SimpleTarget<Bitmap>() {
                            @Override
                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                saveImage(resource);
                            }
                        });
                break;
            case R.id.dialog_img_save_btn_back:
                dismiss();
                break;
        }
    }
    private void saveImage(Bitmap image) {
        String saveImagePath = null;
        Random random = new Random();
        String imageFileName = "JPEG_" + "down" + random.nextInt(10) + ".jpg";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_PICTURES) + "test");

        boolean success = true;
        if(!storageDir.exists()){
            success = storageDir.mkdirs();
        }
        if(success){
            File imageFile = new File(storageDir, imageFileName);
            saveImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fout = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fout);
                fout.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(saveImagePath);
            ToastUtils.showToast(mContext,"保存成功");
            dismiss();
        }
        //        return saveImagePath;
    }
    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        mContext.sendBroadcast(mediaScanIntent);
    }
}
