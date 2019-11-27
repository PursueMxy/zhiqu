package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.session.MediaController;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.CustomVideoView;
import com.icarexm.zhiquwang.utils.RequstUrl;

import java.util.List;

public class ViewPagerAdapter  extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;
    private Context mContext;
    private int Have_video=1;
    private int mCurrentItem=1;



    public ViewPagerAdapter(Context context, List<String> data, ViewPager2 viewPager2,int have_video) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
        this.mContext=context;
        this.Have_video=have_video;
    }

    @NonNull
    @Override
    public ViewPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.viewpage_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPagerAdapter.ViewHolder holder, int position) {
        String animal = mData.get(position);
        if (position==0) {
            if (Have_video ==2) {
                holder.img_play.setVisibility(View.VISIBLE);
            }else {
                holder.img_play.setVisibility(View.GONE);
            }
        }
        Glide.with(mContext).load( animal).into( holder.pager_img);
        holder.img_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.pager_img.setVisibility(View.GONE);
                holder.img_play.setVisibility(View.GONE);
                holder.indicator_video.setVisibility(View.VISIBLE);
                Uri uri = Uri.parse(animal);
                //设置视频控制器
                holder.indicator_video.setVideoURI(uri);
                holder.indicator_video.start();
            }
        });
        holder.indicator_video.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int
                    extra) {
                holder.indicator_video.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
                Log.e("播放","播放异常");
                return true;
            }
        });
    }

    public void refreshData(int heatNum){
       this.mCurrentItem=heatNum;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView pager_img ;
        RelativeLayout relativeLayout;
        private final CustomVideoView indicator_video;
        private final ImageView img_play;

        ViewHolder(View itemView) {
            super(itemView);
            pager_img = itemView.findViewById(R.id.view_pager_img);
            img_play = itemView.findViewById(R.id.view_pager_img_play);
            relativeLayout = itemView.findViewById(R.id.container);
            indicator_video = itemView.findViewById(R.id.view_pager_video);
        }
    }
}

