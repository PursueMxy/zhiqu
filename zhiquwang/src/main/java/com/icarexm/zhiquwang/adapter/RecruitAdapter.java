package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.custview.LabelsView;

import java.util.ArrayList;
import java.util.List;

public class RecruitAdapter extends RecyclerView.Adapter<RecruitAdapter.ViewHolder>{
    private List<String> mData;
    private Context mContext;

    public RecruitAdapter(Context context, List<String> data){
        this.mContext=context;
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.list_home_fm, parent, false);
       ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //LabelsView可以设置任何类型的数据，而不仅仅是String。
        ArrayList<String> label = new ArrayList<>();
        label.add("Android");
        label.add("IOS");
        label.add("前端");
        label.add("后台");
        label.add("微信开发");
        holder.labels.setLabels(label); //直接设置一个字符串数组就可以了。
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LabelsView labels;

        public ViewHolder(View itemView) {
            super(itemView);
            labels = itemView.findViewById(R.id.labels);
            itemView.findViewById(R.id.list_home_fm_rl).setVisibility(View.GONE);
        }
    }
}
