package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icarexm.zhiquwang.R;

public class MyResumeAdapter extends RecyclerView.Adapter<MyResumeAdapter.ViewHolder> {

    public Context mContext;
    public int todayHeatNum=8;

    public MyResumeAdapter(Context context){
        mContext=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.list_resume, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return todayHeatNum;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
