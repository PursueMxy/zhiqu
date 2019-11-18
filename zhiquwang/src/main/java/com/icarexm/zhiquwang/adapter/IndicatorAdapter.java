package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.CustomVideoView;
import com.icarexm.zhiquwang.utils.ToastUtils;

import java.util.List;

public class IndicatorAdapter extends RecyclerView.Adapter<IndicatorAdapter.ViewHolder>{
    private List<String> mData;
    private int mCurrentItem=0;
    private Context mContext;
    private int Have_video=1;

    public IndicatorAdapter(Context context, List<String> data,int currentItem,int have_video){
        this.mCurrentItem=currentItem;
        this.mContext=context;
        this.mData = data;
        this.Have_video=have_video;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.list_indicator, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (position==0){
            if (Have_video==2){
                holder.indicator_img.setVisibility(View.GONE);
            }else {
                holder.indicator_img.setVisibility(View.VISIBLE);
            }
        }
        if (position==mCurrentItem){
                holder.indicator_img.setImageResource(R.drawable.ic_indicator_dot);

        }else {
            holder.indicator_img.setImageResource(R.drawable.ic_circle_dot);
        }

    }
    public void refreshData(int heatNum){
        mCurrentItem=heatNum;
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private final ImageView indicator_img;

        public ViewHolder(View itemView) {
            super(itemView);
            indicator_img = itemView.findViewById(R.id.list_indicator_img);
        }
    }
}
