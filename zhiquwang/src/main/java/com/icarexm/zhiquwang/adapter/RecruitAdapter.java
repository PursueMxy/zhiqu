package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.HomeDataBean;
import com.icarexm.zhiquwang.custview.LabelsView;

import java.util.ArrayList;
import java.util.List;

public class RecruitAdapter extends RecyclerView.Adapter<RecruitAdapter.ViewHolder>{
    private List<HomeDataBean.DataBeanX.DataBean> mData=new ArrayList<>();
    private Context mContext;

    public RecruitAdapter(Context context, List<HomeDataBean.DataBeanX.DataBean> data){
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

    public void SetData(List<HomeDataBean.DataBeanX.DataBean> mData){
        this.mData=mData;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //LabelsView可以设置任何类型的数据，而不仅仅是String。
        View itemView = ((RelativeLayout) holder.itemView).getChildAt(0);
        if (mOnItemClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
        List<HomeDataBean.DataBeanX.DataBean.LabelArrBean> label_arr = mData.get(position).getLabel_arr();
        ArrayList<String> label = new ArrayList<>();
        for (int a=0;a<label_arr.size();a++){
            label.add(label_arr.get(a).getLabel_name());
        }
        holder.labels.setLabels(label);
        holder.tv_position.setText(mData.get(position).getJob_name());
        holder.tv_salary.setText(mData.get(position).getSalary()+"/月"+mData.get(position).getSalary_hour()+"/时");
        holder.tv_age.setText(mData.get(position).getAge());
        holder.tv_address.setText(mData.get(position).getAddress());
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final LabelsView labels;
        private final TextView tv_position;
        private final TextView tv_salary;
        private final TextView tv_age;
        private final TextView tv_address;

        public ViewHolder(View itemView) {
            super(itemView);
            labels = itemView.findViewById(R.id.labels);
            tv_position = itemView.findViewById(R.id.list_home_fm_tv_position);
            tv_salary = itemView.findViewById(R.id.list_home_fm_tv_salary);
            tv_age = itemView.findViewById(R.id.list_home_fm_tv_age);
            tv_address = itemView.findViewById(R.id.list_home_fm_tv_address);
        }
    }

    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
}
