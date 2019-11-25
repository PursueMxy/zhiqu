package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.StatisticsBean;

import java.util.ArrayList;
import java.util.List;

public class ListStatisticsAdapter extends RecyclerView.Adapter<ListStatisticsAdapter.ViewHolder> {

    public Context mContext;
    List<StatisticsBean.DataBean.TotalInfoBean.InfoBean> info=new ArrayList<>();
    public ListStatisticsAdapter(Context context ,List<StatisticsBean.DataBean.TotalInfoBean.InfoBean> info){
        mContext=context;
        this.info=info;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.list_statistics_item, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return info.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
