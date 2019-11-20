package com.icarexm.zhiquwang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.icarexm.zhiquwang.R;
import com.icarexm.zhiquwang.bean.AddResumeBean;

import java.util.List;

public class MyResumeAdapter extends RecyclerView.Adapter<MyResumeAdapter.ViewHolder> {

    public Context mContext;
    List<AddResumeBean> addResumeList;

    public MyResumeAdapter(Context context, List<AddResumeBean> addResumeList){
        mContext=context;
        this.addResumeList=addResumeList;
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
        holder.tv_company_name.setText("公司名称 "+addResumeList.get(position).getCompany_name());
        holder.tv_content.setText(addResumeList.get(position).getJob_content());
        holder.tv_time.setText(addResumeList.get(position).getJob_start()+"-"+addResumeList.get(position).getJob_end());
    }

    @Override
    public int getItemCount() {
        return addResumeList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {


        private final TextView tv_company_name;
        private final TextView tv_content;
        private final TextView tv_time;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_company_name = itemView.findViewById(R.id.list_resume_tv_company_name);
            tv_content = itemView.findViewById(R.id.list_resume_tv_content);
            tv_time = itemView.findViewById(R.id.list_resume_tv_time);
        }
    }
}
