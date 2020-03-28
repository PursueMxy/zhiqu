package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.session.MediaSession;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.CustomVideoView;
import com.icarexm.zhiquwang.view.activity.RecruitDetailActivity;

import java.util.HashMap;
import java.util.List;


public class ViewPagerAdapter  extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private final LocalBroadcastManager localBroadcastManager;
    private final Intent intent;
    private List<String> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private Context mContext;
    private int Have_video=1;
    private int mCurrentItem=0;
    private Bitmap netVideoBitmap;
    private  ViewPagerAdapter.ViewHolder viewHolder;
    private String animal;


    public ViewPagerAdapter(Context context, List<String> data, ViewPager2 viewPager2,int have_video) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
        this.mContext=context;
        this.Have_video=have_video;
        intent = new Intent(RecruitDetailActivity.LOCAL_BROADCAST);
        localBroadcastManager = LocalBroadcastManager.getInstance(mContext);
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.viewpage_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        animal = mData.get(position);
        if (position==0) {
            if (Have_video ==2) {
                netVideoBitmap = getNetVideoBitmap(animal);
                holder.img_play.setVisibility(View.VISIBLE);
                holder.pager_img.setImageBitmap(netVideoBitmap);
            }else {
                holder.img_play.setVisibility(View.GONE);
                Glide.with(mContext).load(animal).into( holder.pager_img);
            }

        }else {
            Glide.with(mContext).load(animal).into(holder.pager_img);
        }

        holder.img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                holder.pager_img.setVisibility(View.GONE);
//                holder.img_play.setVisibility(View.GONE);
                Log.e("执行了","true哈哈哈哈");
                intent.putExtra("collect", true);   //通知fragment,让它去调用queryCity()方法
                localBroadcastManager.sendBroadcast(intent);
            }
        });

    }

    public void refreshData(int heatNum){
       mCurrentItem=heatNum;
       this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pager_img ;
        RelativeLayout relativeLayout;
        private final ImageView img_play;

        ViewHolder(View itemView) {
            super(itemView);
            pager_img = itemView.findViewById(R.id.view_pager_img);
            img_play = itemView.findViewById(R.id.view_pager_img_play);
            relativeLayout = itemView.findViewById(R.id.container);
        }
    }

    //获取网络视频第一帧
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }
}

