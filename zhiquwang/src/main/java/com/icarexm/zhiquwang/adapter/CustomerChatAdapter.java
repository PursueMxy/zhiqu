package com.icarexm.zhiquwang.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.ChatMessageBean;
import com.icarexm.zhiquwang.utils.RequstUrl;
import com.icarexm.zhiquwang.utils.ToastUtils;
import com.icarexm.zhiquwang.view.dialog.ImgBoostDialog;
import com.icarexm.zhiquwang.view.dialog.SeleteMothDialog;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewAdapter;
import com.zhouyou.recyclerview.adapter.HelperRecyclerViewHolder;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import me.panpf.sketch.SketchImageView;

public class CustomerChatAdapter extends HelperRecyclerViewAdapter<ChatMessageBean.NameValuePairsBean> {
    public Context mContext;
    private float beforeScale=1.0f;//之前的伸缩值
    private float nowScale;//当前的伸缩值

    public CustomerChatAdapter(Context context) {
        super(context,R.layout.list_customer_chat_item,R.layout.list_customer_chat_right,R.layout.list_customer_chat_item);
        this.mContext=context;
    }


    @Override
    protected void HelperBindData(HelperRecyclerViewHolder viewHolder, final int position,ChatMessageBean.NameValuePairsBean item) {
        if (item.getSide()==1){
            ImageView img_right = viewHolder.getView(R.id.list_customer_img_right);
            TextView right_tv_content = viewHolder.getView(R.id.list_customer_right_tv_content);
            ImageView right_img_one = viewHolder.getView(R.id.list_customer_right_img_one);
            Glide.with(mContext).load(RequstUrl.URL.HOST+item.getAvatar()).circleCrop().into(img_right);
            if (item.getType()==1) {
                right_img_one.setVisibility(View.GONE);
                right_tv_content.setVisibility(View.VISIBLE);
                right_tv_content.setText(item.getContent());
            }else {
                right_img_one.setVisibility(View.VISIBLE);
                right_tv_content.setVisibility(View.GONE);
                Glide.with(mContext).load(RequstUrl.URL.HOST+item.getContent()).into(right_img_one);
            }
            right_img_one.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImageOnClickListtener.ImageDialog(RequstUrl.URL.HOST+item.getContent());
                }
            });
        }else if(item.getSide()==2){
            ImageView img_left = viewHolder.getView(R.id.list_customer_chat_img_left);
            TextView left_tv_content= viewHolder.getView(R.id.list_customer_chat_left_tv_content);
            ImageView left_img_one = viewHolder.getView(R.id.list_customer_chat_left_img_one);
           Glide.with(mContext).load(RequstUrl.URL.HOST+item.getAvatar()).circleCrop().into(img_left);
                if (item.getType()==1) {
                    left_img_one.setVisibility(View.GONE);
                    left_tv_content.setVisibility(View.VISIBLE);
                    left_tv_content.setText(item.getContent());
                }else {
                    left_img_one.setVisibility(View.VISIBLE);
                    left_tv_content.setVisibility(View.GONE);
                    Glide.with(mContext).load(RequstUrl.URL.HOST+item.getContent()).into(left_img_one);
                }
                left_img_one.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mImageOnClickListtener.ImageDialog(RequstUrl.URL.HOST+item.getContent());
                    }
                });
        }else {

        }



    }

    @Override
    public int checkLayout(ChatMessageBean.NameValuePairsBean item, int position) {
      return item.getSide();
    }

    private ImageOnClickListtener mImageOnClickListtener;

    public ImageOnClickListtener getImageOnClickListtener() {
        return mImageOnClickListtener;
    }

    public void setImageOnClickListtener(ImageOnClickListtener mImageOnClickListtener) {
        this.mImageOnClickListtener = mImageOnClickListtener;
    }


    public interface ImageOnClickListtener {

        void ImageDialog(String url);

    }



}
